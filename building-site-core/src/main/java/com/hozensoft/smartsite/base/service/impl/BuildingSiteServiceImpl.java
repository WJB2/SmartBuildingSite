package com.hozensoft.smartsite.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.base.dao.query.BuildingSiteQuery;
import com.hozensoft.smartsite.base.dao.repo.BuildingSiteRepo;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteQueryDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteValueDto;
import com.hozensoft.smartsite.base.service.BuildingDeveloperService;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.base.transformer.BuildingSiteTransformer;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.dto.PositionDto;
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
public class BuildingSiteServiceImpl implements BuildingSiteService {

    @Autowired
    private BuildingSiteRepo buildingSiteRepo;

    @Autowired
    private BuildingSiteQuery buildingSiteQuery;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @Autowired
    private BuildingDeveloperService buildingDeveloperService;

    @Override
    @Transactional
    public BuildingSiteDto addBuildingSite(ContextHolder holder, BuildingSiteValueDto buildingSiteDto) {
        BuildingSite buildingSite = BuildingSiteTransformer.transformBuildingSiteAddDto2Domain(buildingSiteDto);
        buildingSite.setTenantId(holder.getTenantId());

        PositionDto currentPosition = positionService.findPositionById(holder, buildingSiteDto.getCurrentPositionId());
        buildingSite.setBuildingDeveloperId(currentPosition.getOrgId());
        ContextUtils.applyAwaredContext(currentPosition, buildingSite, CreatedAwared.class, UpdatedAwared.class);

        buildingSiteRepo.addBuildingSite(buildingSite);
        BuildingSiteDto outDto = findBuildingSiteById(holder, buildingSiteDto.getId());

        return outDto;
    }

    @Override
    @Transactional
    public BuildingSiteDto editBuildingSite(ContextHolder holder, BuildingSiteValueDto buildingSiteDto) {

        BuildingSite buildingSite = BuildingSiteTransformer.transformBuildingSiteEditDto2Domain(buildingSiteDto);

        PositionDto currentPosition = positionService.findPositionById(holder, buildingSiteDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, buildingSite, UpdatedAwared.class);

        buildingSiteRepo.patchEditBuildingSite(buildingSite);

        BuildingSiteDto outDto = findBuildingSiteById(holder, buildingSite.getId());

        return outDto;
    }

    @Override
    @Transactional
    public void deleteBuildingSiteById(ContextHolder holder, String buildingSiteId) {

        //buildingSiteRepo.deleteBuildingSiteById(buildingSiteId);

        BuildingSite buildingSite = buildingSiteRepo.loadBuildingSiteById(buildingSiteId);

        buildingSite.setDeletedFlag(!buildingSite.getDeletedFlag());

        buildingSiteRepo.editBuildingSite(buildingSite);
    }

    @Override
    public BuildingSite loadBuildingSiteById(ContextHolder holder, String buildingSiteId) {

        return buildingSiteRepo.loadBuildingSiteById(buildingSiteId);
    }

    @Override
    public BuildingSiteDto findBuildingSiteById(ContextHolder holder, String buildingSiteId) {

        BuildingSiteDto buildingSite = buildingSiteQuery.findBuildingSiteById(buildingSiteId);

        return buildingSite;
    }

    @Override
    public BuildingSiteDto findBuildingSiteByDeviceSn(String deviceSn) {

        AttenceDevice attenceDevice = attenceDeviceService.loadAttenceDeviceBySn(deviceSn);

        return findBuildingSiteById(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(),
                attenceDevice.getBuildingSiteId());
    }

    @Override
    public List<BuildingSiteItemDto> findBuildingSiteList(ContextHolder holder, BuildingSiteQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return buildingSiteQuery.findBuildingSiteList(params);
    }

    @Override
    public PageInfo<BuildingSiteItemDto> findBuildingSitePage(ContextHolder holder, BuildingSiteQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());
        List<BuildingSiteItemDto> list = buildingSiteQuery.findBuildingSiteList(params);

        return new PageInfo<>(list);
    }
}
