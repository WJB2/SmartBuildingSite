package com.hozensoft.smartsite.money.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.money.dto.DepositRecordDto;
import com.hozensoft.smartsite.money.dto.DepositRecordItemDto;
import com.hozensoft.smartsite.money.dto.DepositRecordQueryDto;
import com.hozensoft.smartsite.money.dto.DepositRecordValueDto;
import com.hozensoft.smartsite.money.service.DepositRecordService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/deposit-record")
public class DepositRecordRest {

    @Autowired
    DepositRecordService depositRecordService;

    @PostMapping
    public @ResponseBody void addDepositRecord(@RequestBody DepositRecordValueDto depositRecordDto){

        depositRecordDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        depositRecordService.addDepositRecord(ContextUtils.fetchContext(),depositRecordDto);
    }

    @GetMapping("/page")
    PageInfo<DepositRecordItemDto> findDepositRecordPage(DepositRecordQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }
        return  depositRecordService.findDepositRecordPage(ContextUtils.fetchContext(),params,pageable);
    }
}
