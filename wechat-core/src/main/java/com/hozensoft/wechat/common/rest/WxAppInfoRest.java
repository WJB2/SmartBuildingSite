package com.hozensoft.wechat.common.rest;

import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.wechat.common.dto.WxAppInfoDto;
import com.hozensoft.wechat.common.dto.WxAppInfoValueDto;
import com.hozensoft.wechat.common.service.WxAppInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/wechat/wx-app-info")
@Api(value="微信通用接口")
public class WxAppInfoRest {

    private final static Logger log = LoggerFactory.getLogger(WxAppInfoRest.class);

    @Autowired
    private WxAppInfoService wxAppInfoService;

    @PostMapping
    public @ResponseBody
    WxAppInfoDto addWxAppInfo(@RequestBody WxAppInfoValueDto value){

        return wxAppInfoService.addWxAppInfo(ContextUtils.fetchContext(), value);
    }

    @PutMapping(value="/{id}")
    public @ResponseBody
    WxAppInfoDto editWxAppInfo(@PathVariable("id")String id, @RequestBody WxAppInfoValueDto value){
        value.setId(id);

        return wxAppInfoService.editWxAppInfo(ContextUtils.fetchContext(), value);
    }

    @GetMapping
    public @ResponseBody
    WxAppInfoDto findWxAppInfoByTenant(){

        return wxAppInfoService.findWxAppInfoByTenant(ContextUtils.fetchContext());
    }
}
