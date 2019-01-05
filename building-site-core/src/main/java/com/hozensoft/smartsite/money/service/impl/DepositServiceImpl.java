package com.hozensoft.smartsite.money.service.impl;

import com.github.pagehelper.PageInfo;
import com.hozensoft.exception.ObjectDuplicatedException;
import com.hozensoft.smartsite.money.dao.query.DepositQuery;
import com.hozensoft.smartsite.money.dao.repo.DepositRepo;
import com.hozensoft.smartsite.money.domain.Deposit;
import com.hozensoft.smartsite.money.dto.DepositItemDto;
import com.hozensoft.smartsite.money.dto.DepositQueryDto;
import com.hozensoft.smartsite.money.service.DepositService;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositQuery depositQuery;

    @Autowired
    private DepositRepo depositRepo;

    @Override
    @Transactional
    public void addDeposit(ContextHolder holder, Deposit deposit) {

        Deposit depositOld = loadDepositByBuildingDeveloperId(holder.getTenantId(), deposit.getBuildingDeveloperId());

        if(depositOld!=null){
            throw new ObjectDuplicatedException();
        }

        deposit.setId(IdGen.generate());
        deposit.setTenantId(holder.getTenantId());
        deposit.setDeposit(BigDecimal.ZERO);

        depositRepo.addDeposit(deposit);
    }

    @Override
    @Transactional
    public void editDeposit(ContextHolder holder, Deposit deposit) {

        depositRepo.editDeposit(deposit);
    }

    @Override
    public Deposit loadDepositByBuildingDeveloperId(String tennatId, String buildingDeveloperId) {

        return depositRepo.loadDepositByBuildingDeveloperId(tennatId, buildingDeveloperId);
    }

    @Override
    public PageInfo<DepositItemDto> findDepositPage(ContextHolder holder, DepositQueryDto params, Pageable pageable) {

        params.setTenantId(holder.getTenantId());

        List<DepositItemDto> list = depositQuery.findDepositList(params);

        return new PageInfo<>(list);
    }
}
