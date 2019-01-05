package com.hozensoft.wechat.common.service;

import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.wechat.common.dto.WxAppInfoDto;
import com.hozensoft.wechat.common.dto.WxAppInfoValueDto;

public interface WxAppInfoService {

    WxAppInfoDto addWxAppInfo(ContextHolder holder, WxAppInfoValueDto value);

    WxAppInfoDto editWxAppInfo(ContextHolder holder, WxAppInfoValueDto value);

    WxAppInfoDto findWxAppInfoByTenant(ContextHolder holder);
}
