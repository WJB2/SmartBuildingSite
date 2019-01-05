package com.hozensoft.config.shiro.authc;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;

@Getter
@Setter
public class WechatOpenIDToken implements AuthenticationToken {

    private String tenantId;

    private String staffId;

    @Override
    public Object getPrincipal() {
        return staffId;
    }

    @Override
    public Object getCredentials() {
        return "NULL";
    }
}
