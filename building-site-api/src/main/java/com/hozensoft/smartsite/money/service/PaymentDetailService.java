package com.hozensoft.smartsite.money.service;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.money.domain.PaymentDetail;
import com.hozensoft.smartsite.money.dto.PaymentDetailDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailQueryDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailValueDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

/**
 * 工资发放详情服务类
 */

public interface PaymentDetailService {



    List<PaymentDetailItemDto> findPaymentDetailList(ContextHolder holder,
                                                     PaymentDetailQueryDto params,
                                                     Limitable limitable);
}
