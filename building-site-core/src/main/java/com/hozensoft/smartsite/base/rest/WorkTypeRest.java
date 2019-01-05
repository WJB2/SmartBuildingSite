package com.hozensoft.smartsite.base.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.dto.WorkTypeDto;
import com.hozensoft.smartsite.base.dto.WorkTypeQueryDto;
import com.hozensoft.smartsite.base.service.WorkTypeService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="工种管理接口")
@RestController
@RequestMapping("/api/building-site/work-type")
public class WorkTypeRest {

    @Autowired
    private WorkTypeService buildingSiteService;

    @PostMapping
    @ApiOperation(value="新增工种")
    public @ResponseBody WorkTypeDto addWorkType(@RequestBody WorkTypeDto buildingSiteDto){

        buildingSiteDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return buildingSiteService.addWorkType(ContextUtils.fetchContext(), buildingSiteDto);
    }

    @PutMapping("/{buildingSiteId}")
    public @ResponseBody WorkTypeDto editWorkType(@PathVariable("buildingSiteId")String buildingSiteId,
                                                                    @RequestBody WorkTypeDto buildingSiteDto){

        return buildingSiteService.editWorkType(ContextUtils.fetchContext(), buildingSiteDto);
    }

    @DeleteMapping("/{buildingSiteId}")
    public void deleteWorkTypeById(@PathVariable("buildingSiteId")String buildingSiteId){

        buildingSiteService.deleteWorkTypeById(ContextUtils.fetchContext(), buildingSiteId);
    }

    @GetMapping("/{buildingSiteId}")
    public @ResponseBody WorkTypeDto findWorkTypeById(@PathVariable("buildingSiteId")String buildingSiteId){

        return buildingSiteService.findWorkTypeById(ContextUtils.fetchContext(), buildingSiteId);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<WorkTypeDto> findWorkTypeList(WorkTypeQueryDto params, Limitable limitable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingSiteService.findWorkTypeList(ContextUtils.fetchContext(), params, limitable);
    }

    @GetMapping("/page")
    public @ResponseBody
    PageInfo<WorkTypeDto> findWorkTypePage(WorkTypeQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingSiteService.findWorkTypePage(ContextUtils.fetchContext(), params, pageable);
    }
}
