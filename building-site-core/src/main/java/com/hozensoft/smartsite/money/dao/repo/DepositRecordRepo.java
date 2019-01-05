package com.hozensoft.smartsite.money.dao.repo;


import com.hozensoft.smartsite.money.domain.DepositRecord;
import com.hozensoft.smartsite.money.dto.DepositRecordItemDto;
import com.hozensoft.smartsite.money.dto.DepositRecordQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepositRecordRepo {

    int addDepositRecord(DepositRecord depositRecord);
}
