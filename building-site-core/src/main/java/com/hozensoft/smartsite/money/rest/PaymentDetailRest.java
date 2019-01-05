package com.hozensoft.smartsite.money.rest;

import com.hozensoft.smartsite.money.dto.PaymentDetailDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailItemDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailQueryDto;
import com.hozensoft.smartsite.money.dto.PaymentDetailValueDto;
import com.hozensoft.smartsite.money.service.PaymentDetailService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(value="工资发放明细管理接口")
@RestController
@RequestMapping("/api/building-site/payment-detail/list")

public class PaymentDetailRest {

    @Autowired
    PaymentDetailService paymentDetailService;

    @GetMapping
    public @ResponseBody List<PaymentDetailItemDto> findPaymentDetailList(PaymentDetailQueryDto params, Limitable limitable){

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        return paymentDetailService.findPaymentDetailList(ContextUtils.fetchContext(),
                params, limitable);
    }
}
