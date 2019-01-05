package com.hozensoft.smartsite.attence.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.dao.query.AttenceDeviceQuery;
import com.hozensoft.smartsite.attence.dao.repo.AttenceDeviceRepo;
import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceValueDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.attence.transfermer.AttenceDeviceTransformer;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.event.dispatcher.AttenceDeviceDispatcher;
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
public class AttenceDeviceServiceImpl implements AttenceDeviceService {

    @Autowired
    private AttenceDeviceRepo attenceDeviceRepo;

    @Autowired
    private AttenceDeviceQuery attenceDeviceQuery;

    @Autowired
    private PositionService positionService;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Autowired
    private AttenceDeviceDispatcher attenceDeviceDispatcher;

    @Override
    @Transactional
    public AttenceDeviceDto addAttenceDevice(ContextHolder holder, AttenceDeviceValueDto attenceDeviceDto) {

        AttenceDevice attenceDevice = AttenceDeviceTransformer.transformAttenceDeviceAddDtoToDomain(attenceDeviceDto);
        attenceDevice.setTenantId(holder.getTenantId());

        PositionDto currentPosition = positionService.findPositionById(holder, attenceDeviceDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, attenceDevice, CreatedAwared.class, UpdatedAwared.class);

        BuildingSite buildingSite = buildingSiteService.loadBuildingSiteById(holder, attenceDeviceDto.getBuildingSiteId());
        attenceDevice.setBuildingDeveloperId(buildingSite.getBuildingDeveloperId());

        attenceDeviceRepo.addAttenceDevice(attenceDevice);
        AttenceDeviceDto outDto = findAttenceDeviceById(holder, attenceDevice.getId());

        attenceDeviceDispatcher.dispatchAttenceDeviceCreatedEvent(outDto);

        return outDto;
    }

    @Override
    @Transactional
    public AttenceDeviceDto editAttenceDevice(ContextHolder holder, AttenceDeviceValueDto attenceDeviceDto) {

        AttenceDevice attenceDevice = AttenceDeviceTransformer.transformAttenceDeviceEditDtoToDomain(attenceDeviceDto);

        attenceDevice.setBuildingDeveloperId(null);
        attenceDevice.setBuildingSiteId(null);

        PositionDto currentPosition = positionService.findPositionById(holder, attenceDeviceDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(currentPosition, attenceDevice, UpdatedAwared.class);

        attenceDeviceRepo.patchEditAttenceDevice(attenceDevice);

        AttenceDeviceDto outDto = findAttenceDeviceById(holder, attenceDevice.getId());

        return outDto;
    }

    @Override
    @Transactional
    public void deleteAttenceDeviceById(ContextHolder holder, String attenceDeviceId) {

        attenceDeviceRepo.deleteAttenceDeviceById(holder.getTenantId(), attenceDeviceId);

       /* AttenceDevice attenceDevice = attenceDeviceRepo.loadAttenceDeviceById(holder, attenceDeviceId);

        attenceDevice.setDeletedFlag(true);

        attenceDeviceRepo.patchEditAttenceDevice(attenceDevice);*/
    }

    @Override
    public AttenceDevice loadAttenceDeviceById(ContextHolder holder, String attenceDeviceId) {

        return attenceDeviceRepo.loadAttenceDeviceById(holder.getTenantId(), attenceDeviceId);
    }

    @Override
    public AttenceDevice loadAttenceDeviceBySn(String sn) {

        return attenceDeviceRepo.loadAttenceDeviceBySn(sn);
    }

    @Override
    public AttenceDeviceDto findAttenceDeviceById(ContextHolder holder, String attenceDeviceId) {

        AttenceDeviceDto bdd = attenceDeviceQuery.findAttenceDeviceById(attenceDeviceId);

        return bdd;
    }

    @Override
    public List<AttenceDeviceItemDto> findAttenceDeviceList(ContextHolder holder, AttenceDeviceQueryDto params, Limitable limitable) {
        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return attenceDeviceQuery.findAttenceDeviceList(params);
    }

    @Override
    public PageInfo<AttenceDeviceItemDto> findAttenceDevicePage(ContextHolder holder, AttenceDeviceQueryDto params, Pageable pageable) {
        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());
        List<AttenceDeviceItemDto> list = attenceDeviceQuery.findAttenceDeviceList(params);

        return new PageInfo<>(list);
    }
}
