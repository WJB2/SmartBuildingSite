package com.hozensoft.smartsite.base.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.global.core.domain.Permission;
import com.hozensoft.global.core.dto.PermissionDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperValueDto;
import com.hozensoft.smartsite.base.service.BuildingDeveloperService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.authorization.service.StaffAuthorizationService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.bean.ContextHolder;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/building-developer")
public class BuildingDeveloperRest {

    @Autowired
    private BuildingDeveloperService buildingDeveloperService;

    @Autowired
    private StaffAuthorizationService staffAuthorizationService;

    @PostMapping
    public @ResponseBody BuildingDeveloperDto addBuildingDeveloper(@RequestBody BuildingDeveloperValueDto buildingDeveloperDto){

        buildingDeveloperDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return buildingDeveloperService.addBuildingDeveloper(ContextUtils.fetchContext(), buildingDeveloperDto);
    }

    @PutMapping("/{buildingDeveloperId}")
    public @ResponseBody BuildingDeveloperDto editBuildingDeveloper(@PathVariable("buildingDeveloperId")String buildingDeveloperId,
                                                                    @RequestBody BuildingDeveloperValueDto buildingDeveloperDto){

        buildingDeveloperDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return buildingDeveloperService.editBuildingDeveloper(ContextUtils.fetchContext(), buildingDeveloperDto);
    }

    @DeleteMapping("/{buildingDeveloperId}")
    public void deleteBuildingDeveloperById(@PathVariable("buildingDeveloperId")String buildingDeveloperId){

        buildingDeveloperService.deleteBuildingDeveloperById(ContextUtils.fetchContext(), buildingDeveloperId);
    }

    @GetMapping("/{buildingDeveloperId}")
    public @ResponseBody BuildingDeveloperDto findBuildingDeveloperById(@PathVariable("buildingDeveloperId")String buildingDeveloperId){

        return buildingDeveloperService.findBuildingDeveloperById(ContextUtils.fetchContext(), buildingDeveloperId);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<BuildingDeveloperItemDto> findBuildingDeveloperList(BuildingDeveloperQueryDto params, Limitable limitable){

        ContextHolder holder = ContextUtils.fetchContext();

        List<String> authorizedOrgIdList = staffAuthorizationService.findAuthorizedDataScope(holder, holder.getStaffId(), holder.getPermission(), holder.getPermissionLogical());

        params.setAuthorizedOrgId(authorizedOrgIdList);

        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingDeveloperService.findBuildingDeveloperList(ContextUtils.fetchContext(), params, limitable);
    }

    @GetMapping("/page")
    public @ResponseBody
    PageInfo<BuildingDeveloperItemDto> findBuildingDeveloperPage(BuildingDeveloperQueryDto params, Pageable pageable){

        ContextHolder holder = ContextUtils.fetchContext();

        List<String> authorizedOrgIdList = staffAuthorizationService.findAuthorizedDataScope(holder, holder.getStaffId(), holder.getPermission(), holder.getPermissionLogical());

        params.setAuthorizedOrgId(authorizedOrgIdList);

        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        return buildingDeveloperService.findBuildingDeveloperPage(ContextUtils.fetchContext(), params, pageable);
    }
}
