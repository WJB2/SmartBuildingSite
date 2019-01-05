package com.hozensoft.wechat.common.service.impl;

import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import com.hozensoft.wechat.common.dao.repo.WxAppInfoRepo;
import com.hozensoft.wechat.common.domain.WxAppInfo;
import com.hozensoft.wechat.common.dto.WxAppInfoDto;
import com.hozensoft.wechat.common.dto.WxAppInfoValueDto;
import com.hozensoft.wechat.common.service.WxAppInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxAppInfoServiceImpl implements WxAppInfoService {

    @Autowired
    private WxAppInfoRepo wxAppInfoRepo;

    @Override
    public WxAppInfoDto addWxAppInfo(ContextHolder holder, WxAppInfoValueDto value) {

        value.setId(IdGen.generate());
        value.setTenantId(holder.getTenantId());

        WxAppInfo appInfo = new WxAppInfo();
        BeanUtils.copyProperties(value, appInfo);

        wxAppInfoRepo.addWxAppInfo(appInfo);

        WxAppInfoDto outDto = new WxAppInfoDto();
        BeanUtils.copyProperties(value, outDto);

        return outDto;
    }

    @Override
    public WxAppInfoDto editWxAppInfo(ContextHolder holder, WxAppInfoValueDto value) {

        value.setTenantId(holder.getTenantId());
        WxAppInfo appInfo = new WxAppInfo();
        BeanUtils.copyProperties(value, appInfo);

        wxAppInfoRepo.editWxAppInfo(appInfo);

        WxAppInfoDto outDto = new WxAppInfoDto();
        BeanUtils.copyProperties(value, outDto);

        return outDto;
    }

    @Override
    public WxAppInfoDto findWxAppInfoByTenant(ContextHolder holder) {

        WxAppInfo appInfo = wxAppInfoRepo.loadWxAppInfoByTenant(holder.getTenantId());

        WxAppInfoDto outDto = new WxAppInfoDto();

        if(appInfo!=null){
            BeanUtils.copyProperties(appInfo, outDto);
        }

        return outDto;
    }
}
