package com.hozensoft.wechat.mp.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class MpMsgHandler extends MpAbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                    WxMpService wxMpService, WxSessionManager sessionManager) {

        WxMpXmlOutTextMessage msg = WxMpXmlOutMessage.TEXT().build();
        msg.setToUserName(wxMessage.getFromUser());
        msg.setFromUserName(wxMessage.getToUser());
        msg.setCreateTime(Calendar.getInstance().getTimeInMillis());
        msg.setContent("消息已收到");

        return msg;
    }
}
