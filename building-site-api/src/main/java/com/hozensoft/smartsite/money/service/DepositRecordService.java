package com.hozensoft.smartsite.money.service;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.money.domain.DepositRecord;
import com.hozensoft.smartsite.money.dto.DepositRecordDto;
import com.hozensoft.smartsite.money.dto.DepositRecordItemDto;
import com.hozensoft.smartsite.money.dto.DepositRecordQueryDto;
import com.hozensoft.smartsite.money.dto.DepositRecordValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 存款记录服务类
 */
public interface DepositRecordService {

    /**
     * 新增存款记录
     * */
    void addDepositRecord(ContextHolder holder, DepositRecordValueDto depositRecordId);

    /**
     * 查询存款记录分页列表信息
     */
    PageInfo<DepositRecordItemDto> findDepositRecordPage(ContextHolder holder, DepositRecordQueryDto params,
                                                         Pageable pageable);
}
