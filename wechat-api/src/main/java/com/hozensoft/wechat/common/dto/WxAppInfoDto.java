package com.hozensoft.wechat.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxAppInfoDto {

    private String id;

    private String tenantId;

    private String appId;

    private String appSecret;
}
