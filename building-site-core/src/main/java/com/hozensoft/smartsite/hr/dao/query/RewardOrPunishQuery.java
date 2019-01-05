package com.hozensoft.smartsite.hr.dao.query;

import com.hozensoft.smartsite.hr.dto.RewardOrPunishDto;
import com.hozensoft.smartsite.hr.dto.RewardOrPunishItemDto;
import com.hozensoft.smartsite.hr.dto.RewardOrPunishQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RewardOrPunishQuery {

    RewardOrPunishDto findRewardOrPunishById(@Param("tenantId") String tenantId, @Param("rewardOrPunishId") String rewardOrPunishId);

    List<RewardOrPunishItemDto> findRewardOrPunishList(RewardOrPunishQueryDto params);
}
