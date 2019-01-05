package com.hozensoft.smartsite.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.base.dao.query.WorkTypeQuery;
import com.hozensoft.smartsite.base.dao.repo.WorkTypeRepo;
import com.hozensoft.smartsite.base.domain.WorkType;
import com.hozensoft.smartsite.base.dto.WorkTypeDto;
import com.hozensoft.smartsite.base.dto.WorkTypeQueryDto;
import com.hozensoft.smartsite.base.service.WorkTypeService;
import com.hozensoft.smartsite.base.transformer.WorkTypeTransformer;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Autowired
    private WorkTypeRepo workTypeRepo;

    @Autowired
    private WorkTypeQuery workTypeQuery;

    @Autowired
    private PositionService positionService;

    @Override
    public WorkTypeDto addWorkType(ContextHolder holder, WorkTypeDto workTypeDto) {

        WorkType workType= WorkTypeTransformer.transformWorkTypeAddDto2Domain(workTypeDto);
        workType.setTenantId(holder.getTenantId());

        workType.setBuildingDeveloperId(positionService.findPositionById(holder, workTypeDto.getCurrentPositionId()).getOrgId());

        workTypeRepo.addWorkType(workType);
        WorkTypeDto outDto= findWorkTypeById(holder, workType.getId());

        return outDto;
    }

    @Override
    public WorkTypeDto editWorkType(ContextHolder holder, WorkTypeDto workTypeDto) {

        WorkType workType= WorkTypeTransformer.transformWorkTypeAddDto2Domain(workTypeDto);

        workTypeRepo.patchEditWorkType(workType);

        WorkTypeDto outDto = findWorkTypeById(holder, workType.getId());

        return outDto;
    }

    @Override
    public void deleteWorkTypeById(ContextHolder holder, String workTypeId) {

        workTypeRepo.deleteWorkTypeById(workTypeId);
    }

    @Override
    public WorkType loadWorkTypeById(ContextHolder holder, String workTypeId) {

        return workTypeRepo.loadWorkTypeById(workTypeId);
    }

    @Override
    public WorkTypeDto findWorkTypeById(ContextHolder holder, String workTypeId) {

        return workTypeQuery.findWorkTypeById(workTypeId) ;
    }

    @Override
    public List<WorkTypeDto> findWorkTypeList(ContextHolder holder, WorkTypeQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(),limitable.getLimit());

        return workTypeQuery.findWorkTypeList(params);
    }

    @Override
    public PageInfo<WorkTypeDto> findWorkTypePage(ContextHolder holder, WorkTypeQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());

        List<WorkTypeDto> list= workTypeQuery.findWorkTypeList(params);

        return new PageInfo<>(list);
    }
}
