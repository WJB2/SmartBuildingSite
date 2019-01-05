package com.hozensoft.wechat.common.domain;

import com.hozensoft.wechat.enumeration.WxButtonType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxButton {

    private String id;

    private String parentId;

    private String tenantId;

    private WxButtonType type;

    private String key;

    private String name;

    private String url;

    private String appId;

    private String pagePath;
}
