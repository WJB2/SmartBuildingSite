package com.hozensoft.smartsite.money.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.money.domain.Deposit;
import com.hozensoft.smartsite.money.dto.DepositDto;
import com.hozensoft.smartsite.money.dto.DepositItemDto;
import com.hozensoft.smartsite.money.dto.DepositQueryDto;
import com.hozensoft.smartsite.money.dto.DepositValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;


import java.util.List;

/**
     * 存款信息服务类
     * */

public interface DepositService {

    void addDeposit(ContextHolder holder, Deposit deposit);

    void editDeposit(ContextHolder holder, Deposit deposit);

    Deposit loadDepositByBuildingDeveloperId(String tennatId, String buildingDeveloperId);


    PageInfo<DepositItemDto> findDepositPage(ContextHolder holder, DepositQueryDto params, Pageable pageable);
}
