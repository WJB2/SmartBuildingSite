package com.hozensoft.smartsite.hr.dao.repo;

import com.hozensoft.smartsite.hr.domain.RewardOrPunish;
import org.apache.ibatis.annotations.Param;

public interface RewardOrPunishRepo {

    int addRewardOrPunish(RewardOrPunish rewardOrPunish);

    int editRewardOrPunish(RewardOrPunish rewardOrPunish);

    int patchEditRewardOrPunish(RewardOrPunish rewardOrPunish);

    int deleteRewardOrPunishById(@Param("tenantId") String tenantId, @Param("rewardOrPunishId") String rewardOrPunishId);

    RewardOrPunish loadRewardOrPunishById(@Param("tenantId") String tenantId, @Param("rewardOrPunishId") String rewardOrPunishId);
}
