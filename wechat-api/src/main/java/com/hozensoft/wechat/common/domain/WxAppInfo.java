package com.hozensoft.wechat.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxAppInfo {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 开发者ID
     */
    private String appId;

    /**
     * 开发者秘钥
     */
    private String appSecret;
}
