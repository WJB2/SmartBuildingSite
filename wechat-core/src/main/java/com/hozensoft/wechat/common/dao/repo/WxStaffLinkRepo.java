package com.hozensoft.wechat.common.dao.repo;

import com.hozensoft.wechat.common.domain.WxStaffLink;
import org.apache.ibatis.annotations.Param;

public interface WxStaffLinkRepo {

    int addWxStaffLink(WxStaffLink link);

    WxStaffLink loadWxStaffLinkByAppIdAndOpenId(@Param("wxAppId") String appId,
                                                @Param("openId") String openId);
}
