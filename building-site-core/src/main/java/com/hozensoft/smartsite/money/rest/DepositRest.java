package com.hozensoft.smartsite.money.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.money.dto.DepositItemDto;
import com.hozensoft.smartsite.money.dto.DepositQueryDto;
import com.hozensoft.smartsite.money.service.DepositService;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/deposit")
public class DepositRest {

    @Autowired
    private DepositService depositService ;


    @GetMapping("/page")
    public @ResponseBody
    PageInfo<DepositItemDto> findDepositPage(DepositQueryDto params, Pageable pageable){

        return depositService.findDepositPage(ContextUtils.fetchContext(), params, pageable);
    }

}
