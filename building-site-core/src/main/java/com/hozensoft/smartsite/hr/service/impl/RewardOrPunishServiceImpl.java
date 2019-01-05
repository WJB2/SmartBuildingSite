package com.hozensoft.smartsite.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.exception.ObjectCannotModifiedException;
import com.hozensoft.exception.ObjectNotFoundException;
import com.hozensoft.smartsite.base.domain.BuildingSite;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.hr.dao.query.RewardOrPunishQuery;
import com.hozensoft.smartsite.hr.dao.repo.RewardOrPunishRepo;
import com.hozensoft.smartsite.hr.domain.RewardOrPunish;
import com.hozensoft.smartsite.hr.dto.*;
import com.hozensoft.smartsite.hr.service.RewardOrPunishService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.CreatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RewardOrPunishServiceImpl implements RewardOrPunishService {

    @Autowired
    private RewardOrPunishRepo rewardOrPunishRepo;

    @Autowired
    private RewardOrPunishQuery rewardOrPunishQuery;

    @Autowired
    private PositionService positionService;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Override
    @Transactional
    public RewardOrPunishDto addRewardOrPunish(ContextHolder holder, RewardOrPunishValueDto rewardOrPunishDto) {

        RewardOrPunish rewardOrPunish = new RewardOrPunish();

        BeanUtils.copyProperties(rewardOrPunishDto, rewardOrPunish);
        rewardOrPunish.setId(IdGen.generate());
        rewardOrPunish.setTenantId(holder.getTenantId());
        rewardOrPunish.setApproved(false);

        PositionDto position = positionService.findPositionById(holder, rewardOrPunishDto.getCurrentPositionId());
        ContextUtils.applyAwaredContext(position, rewardOrPunish, CreatedAwared.class);

        BuildingSite buildingSite = buildingSiteService.loadBuildingSiteById(holder, rewardOrPunish.getBuildingSiteId());
        rewardOrPunish.setBuildingDeveloperId(buildingSite.getBuildingDeveloperId());

        rewardOrPunishRepo.addRewardOrPunish(rewardOrPunish);

        return findRewardOrPunishById(holder, rewardOrPunish.getId());
    }

    @Override
    @Transactional
    public RewardOrPunishDto editRewardOrPunish(ContextHolder holder, RewardOrPunishValueDto rewardOrPunishDto) {

        RewardOrPunish rewardOrPunish = loadRewardOrPunish(holder, rewardOrPunishDto.getId());

        if(rewardOrPunish == null){
            throw new ObjectNotFoundException();
        }

        if(Boolean.TRUE.equals(rewardOrPunish.getApproved())){
            throw new ObjectCannotModifiedException();
        }

        BeanUtils.copyProperties(rewardOrPunishDto, rewardOrPunish);

        rewardOrPunishRepo.editRewardOrPunish(rewardOrPunish);

        return findRewardOrPunishById(holder, rewardOrPunishDto.getId());
    }

    @Override
    @Transactional
    public RewardOrPunishDto auditRewardOrPunish(ContextHolder holder, RewardOrPunishAuditValueDto rewardOrPunishAuditDto) {

        RewardOrPunish rewardOrPunish = loadRewardOrPunish(holder, rewardOrPunishAuditDto.getId());

        if(rewardOrPunish == null){
            throw new ObjectNotFoundException();
        }

        if(rewardOrPunish.getApproved()){
            throw new ObjectCannotModifiedException();
        }

        rewardOrPunish.setAuditRemark(rewardOrPunishAuditDto.getAuditRemark());
        rewardOrPunish.setApproved(true);

        PositionDto position = positionService.findPositionById(holder, rewardOrPunishAuditDto.getCurrentPositionId());
        rewardOrPunish.setAuditById(position.getStaffId());
        rewardOrPunish.setAuditOrgId(position.getOrgId());
        rewardOrPunish.setAuditTime(new Date());

        return findRewardOrPunishById(holder, rewardOrPunish.getId());
    }

    @Override
    @Transactional
    public void deleteRewardOrPunish(ContextHolder holder, String rewardOrPunishId) {

        RewardOrPunish rewardOrPunish = loadRewardOrPunish(holder, rewardOrPunishId);

        if(rewardOrPunish.getApproved() != null){
            throw new ObjectCannotModifiedException();
        }

        rewardOrPunishRepo.deleteRewardOrPunishById(holder.getTenantId(), rewardOrPunishId);
    }

    @Override
    public RewardOrPunish loadRewardOrPunish(ContextHolder holder, String rewardOrPunishId) {

        return rewardOrPunishRepo.loadRewardOrPunishById(holder.getTenantId(), rewardOrPunishId);
    }

    @Override
    public RewardOrPunishDto findRewardOrPunishById(ContextHolder holder, String rewardOrPunishId) {

        RewardOrPunishDto rop = rewardOrPunishQuery.findRewardOrPunishById(holder.getTenantId(), rewardOrPunishId);

        return rop;
    }

    @Override
    public List<RewardOrPunishItemDto> findRewardOrPunishList(ContextHolder holder, RewardOrPunishQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.offsetPage(limitable.getOffset(), limitable.getLimit());

        return rewardOrPunishQuery.findRewardOrPunishList(params);
    }

    @Override
    public PageInfo<RewardOrPunishItemDto> findRewardOrPunishPage(ContextHolder holder, RewardOrPunishQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(), pageable.getPageSize());

        List<RewardOrPunishItemDto> list = rewardOrPunishQuery.findRewardOrPunishList(params);

        return new PageInfo<>(list);
    }
}
