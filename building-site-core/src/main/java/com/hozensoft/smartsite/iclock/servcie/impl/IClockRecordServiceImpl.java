package com.hozensoft.smartsite.iclock.servcie.impl;

import com.hozensoft.smartsite.attence.dao.query.AttenceRecordQuery;
import com.hozensoft.smartsite.attence.dao.repo.AttenceRecordRepo;
import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.domain.AttenceRecord;
import com.hozensoft.smartsite.attence.domain.AttenceRule;
import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.dto.EmployeeItemDto;
import com.hozensoft.smartsite.attence.dto.EmployeeQueryDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.attence.service.AttenceRuleService;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.smartsite.iclock.dto.IClockRecordDto;
import com.hozensoft.smartsite.iclock.dto.IdCardClockRecordDto;
import com.hozensoft.smartsite.iclock.service.IClockRecordService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.core.service.PositionService;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.date.DateParseUtils;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IClockRecordServiceImpl implements IClockRecordService {

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttenceRecordQuery attenceRecordQuery;

    @Autowired
    private AttenceRecordRepo attenceRecordRepo;

    @Autowired
    private PositionService positionService;

    @Autowired
    private AttenceRuleService attenceRuleService;

    @Override
    @Transactional
    public int clocking(List<IClockRecordDto> records) {

        if(records==null || records.isEmpty()){
            return 0;
        }

        AttenceDevice attenceDevice = attenceDeviceService.loadAttenceDeviceBySn(records.get(0).getSn());

        if(attenceDevice==null){
            return 0;
        }

        records.stream().forEach((item)->{

            if(item==null){
                return;
            }

            Employee employee = employeeService.loadEmployeeByCode(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), attenceDevice.getBuildingDeveloperId(), attenceDevice.getBuildingSiteId(), item.getCode());

            if(employee==null){
                return;
            }

            Date checkDate = DateParseUtils.parseDate(DateFormatUtils.format(item.getClockTime(), "yyyy-MM-dd"));

            AttenceRecord record = attenceRecordRepo.loadAttenceByEmployeeAndCheckDate(attenceDevice.getTenantId(),
                    checkDate, employee.getBuildingDeveloperId(), employee.getBuildingSiteId(), employee.getId());
            AttenceRule rule = attenceRuleService.loadAttenceRuleByBuildingSiteId(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), employee.getBuildingDeveloperId(), employee.getBuildingSiteId());

            if(record==null){
                record = new AttenceRecord();
                record.init(rule, employee, checkDate);
            }

            record.clock(item);

            if(StringUtils.isBlank(record.getId())){
                record.setId(IdGen.generate());
                attenceRecordRepo.addAttenceRecord(record);
            }else {
                attenceRecordRepo.editAttenceRecord(record);
            }
        });

        return records.size();
    }

    @Override
    @Transactional
    public int clockingWithIdCard(List<IdCardClockRecordDto> records) {

        Map<String, List<IdCardClockRecordDto>> hash = new HashMap<>();

        records.stream().forEach((record)->{
            List<IdCardClockRecordDto> list = hash.get(record.getDeviceSn());

            if(list==null){
                list = new LinkedList<>();
                hash.put(record.getDeviceSn(), list);
            }

            list.add(record);
        });

        hash.entrySet().stream().forEach((item)->{
            AttenceDevice attenceDevice = attenceDeviceService.loadAttenceDeviceBySn(item.getKey().trim());


            if(attenceDevice==null){
                return;
            }

            AttenceRule rule = attenceRuleService.loadAttenceRuleByBuildingSiteId(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), attenceDevice.getBuildingDeveloperId(), attenceDevice.getBuildingSiteId());

            List<String> idCardList = item.getValue().stream().map(i->i.getEmployeeIdCardNo()).collect(Collectors.toList());
            EmployeeQueryDto empParams = EmployeeQueryDto.builder().tenantId(attenceDevice.getTenantId()).deletedFlag(false).idCardNo(idCardList).build();
            List<EmployeeItemDto> empList = employeeService.findEmployeeList(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), empParams, new Limitable());
            Map<String, EmployeeItemDto> empHash = empList.stream().collect(Collectors.toMap(EmployeeItemDto::getIdCardNo, i->i));

            item.getValue().stream().forEach(record->{
                if(record==null){
                    return;
                }

                EmployeeItemDto employee = empHash.get(record.getEmployeeIdCardNo().trim());

                if(employee==null){
                    return;
                }

                Date checkDate = DateParseUtils.parseDate(DateFormatUtils.format(record.getClockingTime(), "yyyy-MM-dd"));

                AttenceRecord attenceRecord = attenceRecordRepo.loadAttenceByEmployeeAndCheckDate(attenceDevice.getTenantId(),
                        checkDate, employee.getBuildingDeveloperId(), employee.getBuildingSiteId(), employee.getId());

                if(attenceRecord==null){
                    attenceRecord = new AttenceRecord();
                    attenceRecord.init(rule, employee, checkDate);
                }

                IClockRecordDto i = new IClockRecordDto();
                i.setClockTime(record.getClockingTime());
                i.setType(record.getType());

                attenceRecord.clock(i);

                if(StringUtils.isBlank(attenceRecord.getId())){
                    attenceRecord.setId(IdGen.generate());
                    attenceRecordRepo.addAttenceRecord(attenceRecord);
                }else {
                    attenceRecordRepo.editAttenceRecord(attenceRecord);
                }
            });
        });

        return records.size();
    }
}
