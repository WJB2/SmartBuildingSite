package com.hozensoft.smartsite.hr.service;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.hr.domain.RewardOrPunish;
import com.hozensoft.smartsite.hr.dto.*;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

public interface RewardOrPunishService {

    RewardOrPunishDto addRewardOrPunish(ContextHolder holder, RewardOrPunishValueDto rewardOrPunishDto);

    RewardOrPunishDto editRewardOrPunish(ContextHolder holder, RewardOrPunishValueDto rewardOrPunishDto);

    RewardOrPunishDto auditRewardOrPunish(ContextHolder holder, RewardOrPunishAuditValueDto rewardOrPunishAuditDto);

    void deleteRewardOrPunish(ContextHolder holder, String rewardOrPunishId);

    RewardOrPunish loadRewardOrPunish(ContextHolder holder, String rewardOrPunishId);

    RewardOrPunishDto findRewardOrPunishById(ContextHolder holder, String rewardOrPunishId);

    List<RewardOrPunishItemDto> findRewardOrPunishList(ContextHolder holder, RewardOrPunishQueryDto params, Limitable limitable);

    PageInfo<RewardOrPunishItemDto> findRewardOrPunishPage(ContextHolder holder, RewardOrPunishQueryDto params, Pageable pageable);
}
