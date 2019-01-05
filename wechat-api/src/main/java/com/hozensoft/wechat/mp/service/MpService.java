package com.hozensoft.wechat.mp.service;

import com.hozensoft.config.shiro.authc.WechatOpenIDToken;
import com.hozensoft.wechat.common.dto.MpCodeToken;

public interface MpService {

    void login(MpCodeToken token);
}
