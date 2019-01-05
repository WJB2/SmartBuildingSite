package com.hozensoft.smartsite.money.dao.repo;

import com.hozensoft.smartsite.money.domain.Payment;
import com.hozensoft.smartsite.money.dto.PaymentDto;
import com.hozensoft.smartsite.money.dto.PaymentItemDto;
import com.hozensoft.smartsite.money.dto.PaymentQueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentRepo {

    int addPayment(Payment payment);
}
