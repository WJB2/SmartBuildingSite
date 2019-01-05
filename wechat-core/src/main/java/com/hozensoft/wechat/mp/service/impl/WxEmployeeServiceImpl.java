package com.hozensoft.wechat.mp.service.impl;

import com.hozensoft.smartsite.attence.dto.EmployeeItemDto;
import com.hozensoft.smartsite.attence.dto.EmployeeQueryDto;
import com.hozensoft.smartsite.attence.dto.EmployeeReportBySiteDto;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.smartsite.base.dto.EmployeeWechatReportDto;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import com.hozensoft.wechat.common.dao.repo.WxAppInfoRepo;
import com.hozensoft.wechat.common.dao.repo.WxStaffLinkRepo;
import com.hozensoft.wechat.common.domain.WxAppInfo;
import com.hozensoft.wechat.common.domain.WxStaffLink;
import com.hozensoft.wechat.mp.dto.WxEmployeeValueDto;
import com.hozensoft.wechat.mp.service.WxEmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class WxEmployeeServiceImpl implements WxEmployeeService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private WxAppInfoRepo wxAppInfoRepo;

    @Autowired
    private WxStaffLinkRepo wxStaffLinkRepo;

    @Override
    @Transactional
    public void addWxEmployee(WxEmployeeValueDto value) {

        WxAppInfo appInfo = wxAppInfoRepo.loadWxAppInfoByAppId(value.getAppId());

        if(StringUtils.isBlank(value.getOpenId())){
            throw new IllegalArgumentException();
        }

        ContextHolder holder = new ContextHolder();
        holder.setTenantId(appInfo.getTenantId());
        EmployeeQueryDto params = EmployeeQueryDto.builder().tenantId(appInfo.getTenantId())
                .idCardNo(Arrays.asList(value.getIdCardNo()))
                .bankCardNo(Arrays.asList(value.getBankNo())).build();

        List<EmployeeItemDto> items = employeeService.findEmployeeList(holder, params, new Limitable());

        EmployeeItemDto employeeDto = null;

        for(EmployeeItemDto item: items){
            if(item.getName().equals(StringUtils.trim(value.getName()))){
                employeeDto = item;
                break;
            }
        }

        if(employeeDto==null){
            throw new RuntimeException("系统内不存在对应的人员，请确认");
        }

        WxStaffLink wxStaffLink = new WxStaffLink();

        wxStaffLink.setId(IdGen.generate());
        wxStaffLink.setTenantId(employeeDto.getTenantId());
        wxStaffLink.setStaffId(employeeDto.getId());
        wxStaffLink.setWxAppId(value.getAppId());
        wxStaffLink.setOpenId(value.getOpenId());

        wxStaffLinkRepo.addWxStaffLink(wxStaffLink);
    }

    @Override
    public void editWxEmployee(WxEmployeeValueDto value) {

    }

    @Override
    public EmployeeWechatReportDto reportEmployee(String appId, String openId, Date beginDate, Date endDate){
        WxStaffLink link = wxStaffLinkRepo.loadWxStaffLinkByAppIdAndOpenId(appId, openId);
        ContextHolder holder = ContextHolder.builder().tenantId(link.getTenantId()).build();

        return employeeService.reportEmployeeAttenceRecordAndSalary(holder, link.getStaffId(), beginDate, endDate);
    }
}
