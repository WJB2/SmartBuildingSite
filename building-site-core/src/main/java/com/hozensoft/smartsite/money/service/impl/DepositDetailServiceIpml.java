package com.hozensoft.smartsite.money.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.enumeration.DepositTypeEnum;
import com.hozensoft.smartsite.money.dao.query.DepositRecordQuery;
import com.hozensoft.smartsite.money.dao.repo.DepositRecordRepo;
import com.hozensoft.smartsite.money.domain.Deposit;
import com.hozensoft.smartsite.money.domain.DepositRecord;
import com.hozensoft.smartsite.money.dto.DepositRecordDto;
import com.hozensoft.smartsite.money.dto.DepositRecordItemDto;
import com.hozensoft.smartsite.money.dto.DepositRecordQueryDto;
import com.hozensoft.smartsite.money.dto.DepositRecordValueDto;
import com.hozensoft.smartsite.money.service.DepositRecordService;
import com.hozensoft.smartsite.money.service.DepositService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.core.dto.PositionDto;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.awared.CreatedAwared;
import com.hozensoft.system.utils.awared.UpdatedAwared;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositDetailServiceIpml  implements DepositRecordService {

    @Autowired
    private DepositService depositService;

    @Autowired
    private DepositRecordRepo depositRecordRepo;

    @Autowired
    private DepositRecordQuery depositRecordQuery;

    @Autowired
    private PositionService positionService;

    @Override
    @Transactional
    public void addDepositRecord(ContextHolder holder, DepositRecordValueDto depositRecordDto){

        Deposit deposit = depositService.loadDepositByBuildingDeveloperId(holder.getTenantId(), depositRecordDto.getBuildingDeveloperId());
        PositionDto positionDto = positionService.findPositionById(holder, depositRecordDto.getCurrentPositionId());

        if(deposit==null){
            deposit = new Deposit();

            deposit.setId(IdGen.generate());
            deposit.setTenantId(holder.getTenantId());
            deposit.setDeposit(BigDecimal.ZERO);
            deposit.setBuildingDeveloperId(depositRecordDto.getBuildingDeveloperId());

            depositService.addDeposit(holder, deposit);
        }

        DepositRecord record = new DepositRecord();

        BeanUtils.copyProperties(depositRecordDto, record);
        record.setId(IdGen.generate());
        record.setInitDeposit(deposit.getDeposit());
        record.setDeposit(depositRecordDto.getMoney());
        record.setTenantId(holder.getTenantId());
        record.setDeletedFlag(false);

        ContextUtils.applyAwaredContext(positionDto, record, CreatedAwared.class, UpdatedAwared.class);
        depositRecordRepo.addDepositRecord(record);

        if(DepositTypeEnum.DEPOSIT.equals(record.getDepositType())){
            deposit.setDeposit(deposit.getDeposit().add(record.getDeposit()));
        }else{
            deposit.setDeposit(deposit.getDeposit().add(record.getDeposit().negate()));
        }

        depositService.editDeposit(holder, deposit);
    }

    @Override
    public PageInfo<DepositRecordItemDto> findDepositRecordPage(ContextHolder holder,DepositRecordQueryDto params,Pageable pageable){

        params.setTenantId(holder.getTenantId());

        PageHelper.startPage(pageable.getPage(),pageable.getPageSize());

        List<DepositRecordItemDto> list = depositRecordQuery.findDepositRecordList(params);

        return new PageInfo<>(list);
    }
}
