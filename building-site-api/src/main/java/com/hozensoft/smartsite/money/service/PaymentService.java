package com.hozensoft.smartsite.money.service;

import com.hozensoft.smartsite.money.dto.*;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工资发放记录服务类
 */

public interface PaymentService {

    /**
     * 新增工资发放记录
     */
    List<PaymentDetailItemDto> addPayment(ContextHolder holder, PaymentValueDto paymentDto, MultipartFile detailExcelFile);

    /**
     * 发放工资
     *
     * @param holder
     * @param paymentDto
     * @param detailExcelFile
     * @return
     */
    List<PaymentDetailItemDto> payoffPayment(ContextHolder holder, PaymentValueDto paymentDto, MultipartFile detailExcelFile);

    /**
     * 生成Excel文档
     *
     * @param holder
     * @param params
     * @return
     */
    XSSFWorkbook generatePaymentExcel(ContextHolder holder, PaymentDetailQueryDto params);

    /**
     * 查询发放详情
     * @param holder
     * @param params
     * @param limit
     * @return
     */
    List<PaymentDetailItemDto> findPaymentDetail(ContextHolder holder, PaymentDetailQueryDto params, Limitable limit);


    /**
     * 根据年月统计工资发放情况
     *
     * @param holder
     * @param yearMonth
     * @return
     */
    List<PaymentReportItemDto> paymentDetailReportByYear(ContextHolder holder, Integer yearMonth);
}
