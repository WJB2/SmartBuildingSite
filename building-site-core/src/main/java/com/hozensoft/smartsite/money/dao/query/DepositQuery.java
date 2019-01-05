package com.hozensoft.smartsite.money.dao.query;

import com.hozensoft.smartsite.money.dto.DepositItemDto;
import com.hozensoft.smartsite.money.dto.DepositQueryDto;

import java.util.List;

public interface DepositQuery {

    List<DepositItemDto> findDepositList(DepositQueryDto params);
}
