package com.hozensoft.smartsite.money.rest;

import com.hozensoft.smartsite.money.dto.*;
import com.hozensoft.smartsite.money.service.PaymentService;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@Api(value="工资支付接口")
@Controller
@RequestMapping("/api/building-site/payment")
public class PaymentRest {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = {RequestMethod.POST})
    public @ResponseBody List<PaymentDetailItemDto> addPayment(HttpServletRequest request, @RequestParam(value="buildingSiteId", required = false) String buildingSiteId,
                                                               @RequestParam(value="yearMonth", required = false)Integer yearMonth,
                                                               @RequestParam(value="file", required = false)MultipartFile file){

        PaymentValueDto paymentDto = new PaymentValueDto();
        paymentDto.setBuildingSiteId(buildingSiteId);
        paymentDto.setYearMonth(yearMonth);

        paymentDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return paymentService.addPayment(ContextUtils.fetchContext(), paymentDto, file);
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "/payoff")
    public @ResponseBody List<PaymentDetailItemDto> payoffPayment(@RequestParam(value="buildingSiteId", required = false) String buildingSiteId,
                                                                  @RequestParam(value="yearMonth", required = false)Integer yearMonth,
                                                                  @RequestParam(value="file", required = false)MultipartFile file){

        PaymentValueDto paymentDto = new PaymentValueDto();
        paymentDto.setBuildingSiteId(buildingSiteId);
        paymentDto.setYearMonth(yearMonth);

        paymentDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return paymentService.payoffPayment(ContextUtils.fetchContext(), paymentDto, file);
    }

    @GetMapping("/excel")
    public void exportPaymentDetail(HttpServletRequest request, HttpServletResponse response,
                                    PaymentDetailQueryDto params) throws IOException {

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        XSSFWorkbook workbook = paymentService.generatePaymentExcel(ContextUtils.fetchContext(), params);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        response.setHeader("Content-disposition", "attachment;filename="
                + new String((workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue() + ".xlsx").getBytes(), "iso-8859-1"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/report")
    public @ResponseBody List<PaymentReportItemDto> reportPaymentDetail(@RequestParam("year") Integer year){

        return paymentService.paymentDetailReportByYear(ContextUtils.fetchContext(), year);
    }
}
