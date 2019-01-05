package com.hozensoft.wechat.mp.rest;

import com.hozensoft.config.shiro.authc.WechatOpenIDToken;
import com.hozensoft.wechat.WechatConsts;
import com.hozensoft.wechat.common.dto.MpCodeToken;
import com.hozensoft.wechat.mp.dto.WxEmployeeValueDto;
import com.hozensoft.wechat.mp.service.MpService;
import com.hozensoft.wechat.mp.service.WxEmployeeService;
import com.hozensoft.wechat.mp.service.impl.MpServiceFactory;
import io.swagger.annotations.Api;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping(value="/api/wechat/mp")
@Api(value="微信公众号接口")
public class MpRest {

    private final static Logger log = LoggerFactory.getLogger(MpRest.class);

    @Value("${server.url}")
    private String serverUrl;

    @Value("${wx.login_url}")
    private String wxLoginUrl;

    @Value("${wx.home_index}")
    private String wxHomeIndex;

    @Autowired
    private MpServiceFactory serviceFactory;

    @Autowired
    private MpService mpService;

    @Autowired
    private WxEmployeeService wxEmployeeService;

    @GetMapping(value="/{appId}.html")
    public @ResponseBody void auth(HttpServletResponse response, @PathVariable("appId")String appId,
                                   @RequestParam(name = "signature", required = false) String signature,
                                   @RequestParam(name = "timestamp", required = false) String timestamp,
                                   @RequestParam(name = "nonce", required = false) String nonce,
                                   @RequestParam(name = "echostr", required = false) String echostr) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        String result = "非法请求";

        log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);

        if(StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)){
            throw new IllegalArgumentException("请求参数非法，请核实");
        }

        final WxMpService wxService = serviceFactory.buildWxMpService(appId);

        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appId));
        }

        if (wxService.checkSignature(timestamp, nonce, signature)) {
            result = echostr;
        }

        response.getWriter().print(result);
    }

    @PostMapping(value="/{appId}.html")
    public @ResponseBody void post(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @PathVariable String appId,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) throws IOException{

        BufferedReader bufferedReader = request.getReader();

        StringBuffer buffer = new StringBuffer();
        String str;

        while((str=bufferedReader.readLine()) != null){
            buffer.append(str);
        }

        String requestBody = buffer.toString();

        response.setContentType("text/xml;charset=utf-8");
        final WxMpService wxService = serviceFactory.buildWxMpService(appId);

        log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;

        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(inMessage, appId);
            if (outMessage == null) {
                out = "";
            }else{
                out = outMessage.toXml();
            }
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);

            log.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.route(inMessage, appId);
            if (outMessage == null) {
                out = "";
            }else{
                out = outMessage.toEncryptedXml(wxService.getWxMpConfigStorage());
            }
        }

        log.debug("\n组装回复信息：{}", out);
        response.getWriter().print(out);
    }

    @GetMapping(value="/{appId}/oauth.html")
    public @ResponseBody void auth(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable("appId")String appId) throws IOException{


        String targetUrl = serverUrl + "/api/wechat/mp/" + appId + "/login.html";

        final WxMpService wxService = serviceFactory.buildWxMpService(appId);

        String redirectUrl=wxService.oauth2buildAuthorizationUrl(targetUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO,null);

        log.info("【微信网页授权】获取code,result={}",redirectUrl);

        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/{appId}/login.html")
    public @ResponseBody void login(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("appId")String appId, @RequestParam("code")String code)
            throws WxErrorException, IOException {

        final WxMpService wxService = serviceFactory.buildWxMpService(appId);

        WxMpOAuth2AccessToken token = wxService.oauth2getAccessToken(code);

        WxMpUser user = wxService.oauth2getUserInfo(token, "zh_CN");

        SecurityUtils.getSubject().getSession(true).setAttribute(WechatConsts.WX_APP_ID, appId);
        SecurityUtils.getSubject().getSession(true).setAttribute(WechatConsts.WX_OPEN_ID, user.getOpenId());

        try {
            MpCodeToken codeToken = new MpCodeToken();

            codeToken.setAppId(appId);
            codeToken.setOpenId(user.getOpenId());

            mpService.login(codeToken);
            response.sendRedirect(wxHomeIndex);
        } catch (Exception ex){
            log.error("登入发生错误", ex);
            response.sendRedirect(wxLoginUrl);
        }
    }

    @PostMapping(value="/register")
    public @ResponseBody void register(HttpServletRequest request, HttpServletResponse response,
                                       @RequestBody WxEmployeeValueDto value) throws IOException {

        value.setAppId((String)SecurityUtils.getSubject().
                getSession(true).getAttribute(WechatConsts.WX_APP_ID));
        value.setOpenId((String)SecurityUtils.getSubject().
                getSession(true).getAttribute(WechatConsts.WX_OPEN_ID));

        wxEmployeeService.addWxEmployee(value);

        try {
            MpCodeToken codeToken = new MpCodeToken();

            codeToken.setAppId(value.getAppId());
            codeToken.setOpenId(value.getOpenId());

            mpService.login(codeToken);
            response.sendRedirect(wxHomeIndex);
        } catch (Exception ex){
            log.error("登入发生错误", ex);
            response.sendRedirect(wxLoginUrl);
        }
    }

    private WxMpXmlOutMessage route(WxMpXmlMessage message, String appId) {

        try {
            return serviceFactory.buildMessageRouter(serviceFactory.buildWxMpService(appId)).route(message);
        } catch (Exception e) {
            log.error("路由消息时出现异常！", e);
        }

        return null;
    }
}
