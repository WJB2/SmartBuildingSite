package com.hozensoft.wechat.mp.service.impl;

import com.hozensoft.config.shiro.authc.WechatOpenIDToken;
import com.hozensoft.wechat.common.dao.repo.WxStaffLinkRepo;
import com.hozensoft.wechat.common.domain.WxStaffLink;
import com.hozensoft.wechat.common.dto.MpCodeToken;
import com.hozensoft.wechat.mp.service.MpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;

@Service
public class MpServiceImpl implements MpService {

    @Autowired
    private WxStaffLinkRepo wxStaffLinkRepo;

    @Override
    public void login(MpCodeToken token) {

        WxStaffLink link = wxStaffLinkRepo.loadWxStaffLinkByAppIdAndOpenId(token.getAppId(), token.getOpenId());

        if(link==null){
            throw new UnknownAccountException();
        }

        WechatOpenIDToken wechatOpenIDToken = new WechatOpenIDToken();

        wechatOpenIDToken.setTenantId(link.getTenantId());
        wechatOpenIDToken.setStaffId(link.getStaffId());

        SecurityUtils.getSubject().login(wechatOpenIDToken);
    }
}
