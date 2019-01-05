package com.hozensoft.wechat.mp.rest;

import com.hozensoft.config.rest.converter.DateConverter;
import com.hozensoft.smartsite.attence.dto.EmployeeReportBySiteDto;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.wechat.WechatConsts;
import com.hozensoft.wechat.mp.service.WxEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/api/wechat/employee")
public class WxEmployeeRest {

    @Autowired
    private WxEmployeeService employeeService;

    @GetMapping("/report/employee-attence-record-and-salary")
    public @ResponseBody
    EmployeeWechatReportDto reportEmployeeAttenceRecordAndSalary(
            @RequestParam("beginDate") Date beginDate,
            @RequestParam("endDate") Date endDate){


        return employeeService.reportEmployee((String)SecurityUtils.getSubject().
                getSession(true).getAttribute(WechatConsts.WX_APP_ID),
                (String)SecurityUtils.getSubject().
                        getSession(true).getAttribute(WechatConsts.WX_OPEN_ID),
                beginDate, endDate);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConverter());
    }
}
