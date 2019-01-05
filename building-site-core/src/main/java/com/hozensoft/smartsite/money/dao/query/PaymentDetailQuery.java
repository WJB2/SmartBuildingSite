package com.hozensoft.smartsite.money.dao.query;

import com.hozensoft.smartsite.money.dto.PaymentDetailDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailQueryDto;
import com.hozensoft.smartsite.money.dto.PaymentReportCellDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentDetailQuery {

    List<PaymentDetailItemDto> findPaymentDetailList(PaymentDetailQueryDto params);

    List<PaymentReportCellDto> findPaymentReportCellList(@Param("tenantId") String tenantId, @Param("year") Integer year);
}
