package com.hozensoft.wechat.common.rest;

import com.hozensoft.wechat.common.dto.WxButtonDto;
import com.hozensoft.wechat.common.dto.WxButtonQueryDto;
import com.hozensoft.wechat.common.dto.WxButtonTreeDto;
import com.hozensoft.wechat.common.dto.WxButtonValueDto;
import com.hozensoft.wechat.common.service.WxButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wechat/button")
public class WxButtonRest {

    //@Autowired
    private WxButtonService buttonService;

    @PostMapping
    public @ResponseBody
    WxButtonDto addButton(WxButtonValueDto buttonDto){

        return null;
    }

    @PutMapping("/{buttonId}")
    public @ResponseBody
    WxButtonDto editButton(@PathVariable("buttonId")String buttonId, WxButtonValueDto buttonDto){

        return null;
    }

    @DeleteMapping("/{buttonId}")
    public @ResponseBody void deleteButtonById(@PathVariable("buttonId")String buttonId){

    }

    @GetMapping("/tree")
    public @ResponseBody
    List<WxButtonTreeDto> findButtonTree(WxButtonQueryDto params){

        return null;
    }
}
