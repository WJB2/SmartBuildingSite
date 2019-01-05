package com.hozensoft.smartsite.money.dao.repo;

import com.hozensoft.smartsite.enumeration.PaymentStatusEnum;
import com.hozensoft.smartsite.money.domain.PaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentDetailRepo {

    int addPaymentDetailList(@Param("list") List<PaymentDetail> paymentDetailList);

    int editPaymentDetailStatus(@Param("tenantId") String tenantId,
                                @Param("buildingDeveloperId") String buildingDeveloperId,
                                @Param("buildingSiteId") String buildingSiteId,
                                @Param("idCardNo") List<String> idCardNo,
                                @Param("yearMonth") Integer yearMonth,
                                @Param("status")PaymentStatusEnum status);

    List<PaymentDetail> loadPaymentDetailList(@Param("tenantId") String tenantId,
                                              @Param("buildingDeveloperId") String buildingDeveloperId,
                                              @Param("buildingSiteId") String buildingSiteId,
                                              @Param("idCardNo") List<String> idCardNo,
                                              @Param("yearMonth") Integer yearMonth);

    int editPaymentDetailMoney(@Param("tenantId") String tenantId,
                                @Param("id") String id,
                                @Param("money") BigDecimal money);
}
