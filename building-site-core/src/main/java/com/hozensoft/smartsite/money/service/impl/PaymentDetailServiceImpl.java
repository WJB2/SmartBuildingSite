package com.hozensoft.smartsite.money.service.impl;

import com.hozensoft.smartsite.money.dao.query.PaymentDetailQuery;
import com.hozensoft.smartsite.money.dao.repo.PaymentDetailRepo;
import com.hozensoft.smartsite.money.domain.PaymentDetail;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailQueryDto;
import com.hozensoft.smartsite.money.service.PaymentDetailService;
import com.hozensoft.smartsite.money.service.PaymentService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    @Autowired
    private PaymentDetailQuery paymentDetailQuery;

    @Autowired
    private PaymentDetailRepo paymentDetailRepo;

    @Override
    public List<PaymentDetailItemDto> findPaymentDetailList(ContextHolder holder, PaymentDetailQueryDto params, Limitable limitable) {

        params.setTenantId(holder.getTenantId());

        return paymentDetailQuery.findPaymentDetailList(params);
    }
}
