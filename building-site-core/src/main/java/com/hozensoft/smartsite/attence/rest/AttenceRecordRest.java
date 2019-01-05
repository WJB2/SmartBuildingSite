package com.hozensoft.smartsite.attence.rest;

import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.attence.service.AttenceRecordService;
import com.hozensoft.system.utils.ContextUtils;
import com.hozensoft.system.utils.bean.ContextHolder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/building/attence-record")
public class AttenceRecordRest {

    @Autowired
    private AttenceRecordService attenceRecordService;

    @GetMapping("/manual-clocking-employee")
    public @ResponseBody
    List<BuildingSiteWithEmployeeDto> findEmployeeForManualClocking(){

        return attenceRecordService.findEmployeeForManualClocking(ContextUtils.fetchContext(),
                ContextUtils.getCurrentOrganization().getId(), ContextUtils.getCurrentPosition().getStaffId());
    }

    @PostMapping("/manual-clocking")
    public @ResponseBody void manualClocking(@RequestBody EmployeeItemDto employee){

        attenceRecordService.clocking(ContextUtils.fetchContext(), ContextUtils.getCurrentPosition().getId(), employee);
    }

    @PostMapping("/manual-repair-clocking")
    public @ResponseBody void manualRepairClocking(@RequestBody AttenceRecordRepairDto repairDto){

        attenceRecordService.repairClocking(ContextUtils.fetchContext(), repairDto);
    }

    @GetMapping("/attence-detail")
    public @ResponseBody List<BuildingSiteWithCheckDetail>  findBuildingSiteCheckDetails(AttenceRecordQueryDto params){

        return attenceRecordService.findBuildingSiteCheckDetails(ContextUtils.fetchContext(), params);
    }

    @GetMapping("/salary-report")
    public void generateSalaryReportExcel(HttpServletResponse response, AttenceRecordQueryDto params) throws IOException {

        XSSFWorkbook workbook = attenceRecordService.generateSalaryReportExcel(ContextUtils.fetchContext(), params);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        response.setHeader("Content-disposition", "attachment;filename="
                + new String((workbook.getSheetAt(0).getRow(0).getCell(0).getStringCellValue() + ".xlsx").getBytes(), "iso-8859-1"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
