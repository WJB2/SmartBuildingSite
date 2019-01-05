package com.hozensoft.wechat.mp.service;

import com.hozensoft.smartsite.attence.dto.EmployeeReportBySiteDto;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.wechat.mp.dto.WxEmployeeValueDto;

import java.util.Date;
import java.util.List;

public interface WxEmployeeService {

    /**
     * 新增人员信息
     *
     * @param value
     */
    void addWxEmployee(WxEmployeeValueDto value);

    /**
     * 编辑人员信息
     * @param value
     */
    void editWxEmployee(WxEmployeeValueDto value);

    EmployeeWechatReportDto reportEmployee(String appId, String openId, Date beginDate, Date endDate);
}
