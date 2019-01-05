package com.hozensoft.wechat.common.service;

import com.hozensoft.wechat.common.dto.WxButtonDto;
import com.hozensoft.wechat.common.dto.WxButtonQueryDto;
import com.hozensoft.wechat.common.dto.WxButtonTreeDto;
import com.hozensoft.wechat.common.dto.WxButtonValueDto;

import java.util.List;

public interface WxButtonService {

    WxButtonDto addButton(String tenantId, WxButtonValueDto buttonDto);

    WxButtonDto editButton(String tenantId, WxButtonValueDto buttonDto);

    WxButtonDto deleteButtonById(String tenantId, WxButtonValueDto buttonDto);

    List<WxButtonTreeDto> findButtonTree(String tenantId, WxButtonQueryDto params);
}
