package com.hozensoft.wechat.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxStaffLink {

    private String id;

    private String tenantId;

    private String staffId;

    private String wxAppId;

    private String openId;
}
