package com.hozensoft.smartsite.base.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteValueDto;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="工地管理接口")
@RestController
@RequestMapping("/api/building-site/building-site")
public class BuildingSiteRest {

    @Autowired
    private BuildingSiteService buildingSiteService;

    @PostMapping
    public @ResponseBody BuildingSiteDto addBuildingSite(@RequestBody BuildingSiteValueDto buildingSiteDto){

        buildingSiteDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return buildingSiteService.addBuildingSite(ContextUtils.fetchContext(), buildingSiteDto);
    }

    @PutMapping("/{buildingSiteId}")
    public @ResponseBody BuildingSiteDto editBuildingSite(@PathVariable("buildingSiteId")String buildingSiteId,
                                                                    @RequestBody BuildingSiteValueDto buildingSiteDto){

        buildingSiteDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return buildingSiteService.editBuildingSite(ContextUtils.fetchContext(), buildingSiteDto);
    }

    @DeleteMapping("/{buildingSiteId}")
    public void deleteBuildingSiteById(@PathVariable("buildingSiteId")String buildingSiteId){

        buildingSiteService.deleteBuildingSiteById(ContextUtils.fetchContext(), buildingSiteId);
    }

    @GetMapping("/{buildingSiteId}")
    public @ResponseBody BuildingSiteDto findBuildingSiteById(@PathVariable("buildingSiteId")String buildingSiteId){

        return buildingSiteService.findBuildingSiteById(ContextUtils.fetchContext(), buildingSiteId);
    }



    @GetMapping("/list")
    public @ResponseBody
    List<BuildingSiteItemDto> findBuildingSiteList(BuildingSiteQueryDto params, Limitable limitable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingSiteService.findBuildingSiteList(ContextUtils.fetchContext(), params, limitable);
    }

    @GetMapping("/page")
    public @ResponseBody
    PageInfo<BuildingSiteItemDto> findBuildingSitePage(BuildingSiteQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingSiteService.findBuildingSitePage(ContextUtils.fetchContext(), params, pageable);
    }
}
