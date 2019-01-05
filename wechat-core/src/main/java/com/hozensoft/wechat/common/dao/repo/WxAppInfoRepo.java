package com.hozensoft.wechat.common.dao.repo;

import com.hozensoft.wechat.common.domain.WxAppInfo;
import org.apache.ibatis.annotations.Param;

public interface WxAppInfoRepo {

    int addWxAppInfo(WxAppInfo appInfo);

    int editWxAppInfo(WxAppInfo appInfo);

    int patchEditWxAppInfo(WxAppInfo appInfo);

    int deleteWxAppInfoById(@Param("id") String id);

    WxAppInfo loadWxAppInfoById(@Param("id") String id);

    WxAppInfo loadWxAppInfoByTenant(@Param("tenantId") String tenantId);

    WxAppInfo loadWxAppInfoByAppId(@Param("appId") String appId);
}
