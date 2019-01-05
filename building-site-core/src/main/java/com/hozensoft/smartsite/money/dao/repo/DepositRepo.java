package com.hozensoft.smartsite.money.dao.repo;

import com.hozensoft.smartsite.money.domain.Deposit;
import com.hozensoft.smartsite.money.dto.DepositDto;
import com.hozensoft.smartsite.money.dto.DepositItemDto;
import com.hozensoft.smartsite.money.dto.DepositQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepositRepo {

    int addDeposit(Deposit deposit);

    int editDeposit(Deposit deposit);

    Deposit loadDepositByBuildingDeveloperId(@Param("tenantId") String tenantId, @Param("buildingDeveloperId") String buildingDeveloperId);
}
