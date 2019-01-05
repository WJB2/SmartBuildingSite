package com.hozensoft.smartsite.money.dao.query;

import com.hozensoft.smartsite.money.dto.DepositRecordDto;
import com.hozensoft.smartsite.money.dto.DepositRecordItemDto;
import com.hozensoft.smartsite.money.dto.DepositRecordQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepositRecordQuery {

    List<DepositRecordItemDto> findDepositRecordList(DepositRecordQueryDto params);
}
