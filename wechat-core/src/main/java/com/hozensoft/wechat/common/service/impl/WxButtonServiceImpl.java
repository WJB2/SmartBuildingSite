package com.hozensoft.wechat.common.service.impl;

import com.hozensoft.wechat.common.dto.WxButtonDto;
import com.hozensoft.wechat.common.dto.WxButtonQueryDto;
import com.hozensoft.wechat.common.dto.WxButtonTreeDto;
import com.hozensoft.wechat.common.dto.WxButtonValueDto;
import com.hozensoft.wechat.common.service.WxButtonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxButtonServiceImpl implements WxButtonService {

    @Override
    public WxButtonDto addButton(String tenantId, WxButtonValueDto buttonDto) {
        return null;
    }

    @Override
    public WxButtonDto editButton(String tenantId, WxButtonValueDto buttonDto) {
        return null;
    }

    @Override
    public WxButtonDto deleteButtonById(String tenantId, WxButtonValueDto buttonDto) {
        return null;
    }

    @Override
    public List<WxButtonTreeDto> findButtonTree(String tenantId, WxButtonQueryDto params) {
        return null;
    }
}
