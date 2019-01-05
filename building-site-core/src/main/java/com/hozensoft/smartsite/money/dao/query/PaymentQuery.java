package com.hozensoft.smartsite.money.dao.query;

import com.hozensoft.smartsite.money.dto.PaymentDto;
import com.hozensoft.smartsite.money.dto.PaymentItemDto;
import com.hozensoft.smartsite.money.dto.PaymentQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentQuery {

    PaymentDto findPaymentById(@Param("paymentId")String paymentId);

    List<PaymentItemDto> findPaymentByList(PaymentQueryDto params);
}
