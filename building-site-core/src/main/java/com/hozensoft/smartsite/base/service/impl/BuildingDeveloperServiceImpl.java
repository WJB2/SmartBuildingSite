package com.hozensoft.smartsite.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.dao.query.BuildingDeveloperQuery;
import com.hozensoft.smartsite.base.dao.repo.BuildingDeveloperRepo;
import com.hozensoft.smartsite.base.domain.BuildingDeveloper;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperValueDto;
import com.hozensoft.smartsite.base.service.BuildingDeveloperService;
import com.hozensoft.smartsite.base.transformer.BuildingDeveloperTransformer;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.dto.OrganizationDto;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.OrganizationService;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.CreatedAwared;
import com.hozensoft.system.utils.awared.UpdatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuildingDeveloperServiceImpl implements BuildingDeveloperService {

    @Autowired
    private BuildingDeveloperRepo buildingDeveloperRepo;

    @Autowired
    private BuildingDeveloperQuery buildingDeveloperQuery;

    @Autowired
    private PositionService positionService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    @Transactional
    public BuildingDeveloperDto addBuildingDeveloper(ContextHolder holder, BuildingDeveloperValueDto buildingDeveloperDto) {

        OrganizationDto organization = organizationService.findOrganizationById(holder, buildingDeveloperDto.getOrgId());

        BuildingDeveloper buildingDeveloper = BuildingDeveloperTransformer.transformBuildingDeveloperAddtoDomain(buildingDeveloperDto);
        buildingDeveloper.setTenantId(holder.getTenantId());
        buildingDeveloper.setDeletedFlag(false);
        buildingDeveloper.setName(organization.getName());
        buildingDeveloper.setNamePinyin(organization.getNamePinyin());
        buildingDeveloper.setCreditCode(organization.getCode());

        PositionDto currentPosition = positionService.findPositionById(holder, buildingDeveloperDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, buildingDeveloper, CreatedAwared.class, UpdatedAwared.class);

        buildingDeveloperRepo.addBuildingDeveloper(buildingDeveloper);
        BuildingDeveloperDto outDto = findBuildingDeveloperById(holder, buildingDeveloperDto.getId());

        return outDto;
    }

    @Override
    @Transactional
    public BuildingDeveloperDto editBuildingDeveloper(ContextHolder holder, BuildingDeveloperValueDto buildingDeveloperDto) {

        OrganizationDto organization = organizationService.findOrganizationById(holder, buildingDeveloperDto.getOrgId());

        BuildingDeveloper buildingDeveloper = BuildingDeveloperTransformer.transforBuildingDeveloperEdittoDomain(buildingDeveloperDto);
        buildingDeveloper.setTenantId(holder.getTenantId());
        buildingDeveloper.setName(organization.getName());
        buildingDeveloper.setNamePinyin(organization.getNamePinyin());
        buildingDeveloper.setCreditCode(organization.getCode());

        PositionDto currentPosition = positionService.findPositionById(holder, buildingDeveloperDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, buildingDeveloper, UpdatedAwared.class);

        buildingDeveloperRepo.patchEditBuildingDeveloper(buildingDeveloper);

        BuildingDeveloperDto outDto = findBuildingDeveloperById(holder, buildingDeveloper.getId());

        return outDto;
    }

    @Override
    @Transactional
    public void deleteBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId) {

        //buildingDeveloperRepo.deleteBuildingDeveloperById(buildingDeveloperId);

        BuildingDeveloper buildingDeveloper = buildingDeveloperRepo.loadBuildingDeveloperById(buildingDeveloperId);

        buildingDeveloper.setDeletedFlag(!buildingDeveloper.getDeletedFlag());

        buildingDeveloperRepo.editBuildingDeveloper(buildingDeveloper);
    }

    @Override
    public BuildingDeveloper loadBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId) {

        return buildingDeveloperRepo.loadBuildingDeveloperById(buildingDeveloperId);

    }

    @Override
    public BuildingDeveloperDto findBuildingDeveloperById(ContextHolder holder, String buildingDeveloperId) {

        BuildingDeveloperDto bdd = buildingDeveloperQuery.findBuildingDeveloperById(buildingDeveloperId);

        return bdd;
    }

    @Override
    public List<BuildingDeveloperItemDto> findBuildingDeveloperList(ContextHolder holder, BuildingDeveloperQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return buildingDeveloperQuery.findBuildingDeveloperList(params);
    }

    @Override
    public PageInfo<BuildingDeveloperItemDto> findBuildingDeveloperPage(ContextHolder holder, BuildingDeveloperQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());

        List<BuildingDeveloperItemDto> list = buildingDeveloperQuery.findBuildingDeveloperList(params);

        return new PageInfo<>(list);
    }
}
