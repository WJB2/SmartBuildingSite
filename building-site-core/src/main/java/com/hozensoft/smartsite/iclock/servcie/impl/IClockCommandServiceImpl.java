package com.hozensoft.smartsite.iclock.servcie.impl;

import com.google.common.collect.Lists;
import com.hozensoft.smartsite.attence.dao.repo.EmployeeFeatureRepo;
import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.domain.Employee;
import com.hozensoft.smartsite.attence.domain.EmployeeFeature;
import com.hozensoft.smartsite.attence.dto.*;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.attence.service.EmployeeService;
import com.hozensoft.smartsite.base.domain.BuildingDeveloper;
import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.base.service.BuildingDeveloperService;
import com.hozensoft.smartsite.base.service.BuildingSiteService;
import com.hozensoft.smartsite.iclock.dao.query.IClockCommandQuery;
import com.hozensoft.smartsite.iclock.dao.repo.IClockCommandRepo;
import com.hozensoft.smartsite.iclock.domain.IClockCommand;
import com.hozensoft.smartsite.iclock.dto.IClockCommandItemDto;
import com.hozensoft.smartsite.iclock.service.IClockCommandService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IClockCommandServiceImpl implements IClockCommandService {

    @Autowired
    private IClockCommandQuery iClockCommandQuery;

    @Autowired
    private IClockCommandRepo iClockCommandRepo;

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeFeatureRepo employeeFeatureRepo;

    @Autowired
    private BuildingSiteService buildingSiteService;

    @Override
    @Transactional
    public List<IClockCommandItemDto> findIClockCommandBySn(String sn) {

        return iClockCommandQuery.findIClockCommandListBySn(sn);
    }

    @Override
    @Transactional
    public void addIClockCommand(List<IClockCommand> iClockCommand) {

        if(CollectionUtils.isEmpty(iClockCommand)){
            return;
        }

        iClockCommandRepo.addIClockCommandList(iClockCommand);
    }

    @Override
    @Transactional
    public void deleteIClockCommandById(List<String> commandIdList) {

        iClockCommandRepo.deleteIClockCommandById(commandIdList);
    }

    @Override
    @Transactional
    public void syncCommand(String sn, String command) {

        if(sn.indexOf(".")>=0){
            return;
        }

        if(command.startsWith("OPLOG")){
            return;
        }

        AttenceDevice attenceDevice = attenceDeviceService.loadAttenceDeviceBySn(sn);

        int eqIndex = command.indexOf("=");
        int tabIndex = command.indexOf("\t");

        String code = command.substring(eqIndex+1, tabIndex);

        EmployeeDto emp = employeeService.findEmployeeByCode(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), attenceDevice.getBuildingDeveloperId(), code);

        if(emp==null){
            return;
        }

        AttenceDeviceQueryDto params = AttenceDeviceQueryDto.builder().
                tenantId(attenceDevice.getTenantId()).
                buildingDeveloperId(Arrays.asList(attenceDevice.getBuildingDeveloperId())).
                buildingSiteId(Arrays.asList(attenceDevice.getBuildingSiteId())).
                deletedFlag(false).build();

        List<AttenceDeviceItemDto> devices = attenceDeviceService.findAttenceDeviceList(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), params, new Limitable());

        for(int i=0; i<devices.size(); i++){
            AttenceDeviceItemDto item = devices.get(i);

            if(!item.getSn().equals(sn)){
                List<IClockCommand> commands = processCommand(command, item.getSn());

                addIClockCommand(commands);
            }
        }

        List<EmployeeFeature> employeeFeatureList = employeeFeatureRepo.loadEmployeeFeatureList(Arrays.asList(emp.getIdCardNo()));

        if(CollectionUtils.isEmpty(employeeFeatureList)){
            EmployeeFeature employeeFeature = new EmployeeFeature();
            employeeFeature.setId(IdGen.generate());
            employeeFeature.setIdCardNo(emp.getIdCardNo());
            employeeFeature.setFingerprint(null);
            employeeFeature.setFace(null);

            employeeFeatureRepo.insertEmployeeFeature(employeeFeature);
        }

        persistFingerprint(attenceDevice, emp, command);
        persistProcessFace(attenceDevice, emp, command);
        persistProcessPhoto(attenceDevice, emp, command);
    }

    @Override
    @Transactional
    public void initDevice(AttenceDeviceDto attenceDevice) {

        if(attenceDevice.getSn().indexOf(".")>=0){
            return;
        }

        AttenceDeviceQueryDto params = AttenceDeviceQueryDto.builder().
                tenantId(attenceDevice.getTenantId()).
                buildingDeveloperId(Arrays.asList(attenceDevice.getBuildingDeveloperId())).
                buildingSiteId(Arrays.asList(attenceDevice.getBuildingSiteId())).
                deletedFlag(false).build();

        EmployeeQueryDto employeeParams = EmployeeQueryDto.builder().tenantId(attenceDevice.getTenantId())
                .buildingSiteId(Arrays.asList(attenceDevice.getBuildingSiteId())).deletedFlag(false).build();

        List<EmployeeItemDto> employeeList = employeeService.findEmployeeList(ContextHolder.builder().tenantId(attenceDevice.getTenantId()).build(), employeeParams, new Limitable());

        if(employeeList.isEmpty()){
            return;
        }

        List<IClockCommand> commandList = new LinkedList<>();

        employeeList.stream().forEach((emp)->{
            IClockCommand command = new IClockCommand();

            command.setId(getAccountIdByUUId());
            command.setSn(attenceDevice.getSn());

            StringBuffer cmd = new StringBuffer();
            cmd.append("C:");
            cmd.append(command.getId() + ":DATA UPDATE USERINFO PIN=" + emp.getCode() + "\t");
            cmd.append("Name=" + emp.getName() + "\t");
            cmd.append("Passwd=\t");
            cmd.append("Card=\t");
            cmd.append("Grp=1\t");
            cmd.append("TZ=0000000000000000\t");
            cmd.append("Pri=0\t");
            command.setCommand(cmd.toString());

            commandList.add(command);
        });

        List<String> employeeIdCardNoList = employeeList.stream().map(item->item.getIdCardNo()).collect(Collectors.toList());

        List<EmployeeFeature> featureList = employeeFeatureRepo.loadEmployeeFeatureList(employeeIdCardNoList);

        featureList.stream().forEach(feature->{
           if(StringUtils.isNotBlank(feature.getFace())){
               List<IClockCommand> faceCmds = processCommand(feature.getFingerprint(), attenceDevice.getSn());

               commandList.addAll(faceCmds);
           }

           if(StringUtils.isNotBlank(feature.getFingerprint())){
               List<IClockCommand> fingerprint = processCommand(feature.getFingerprint(), attenceDevice.getSn());

               commandList.addAll(fingerprint);
           }
        });

        List<List<IClockCommand>> lists = Lists.partition(commandList, 50);

        lists.stream().forEach((list)->{
            if(!CollectionUtils.isEmpty(list)){
                iClockCommandRepo.addIClockCommandList(list);
            }
        });
    }

    @Override
    public void createEmployee(EmployeeDto employeeDto){
        AttenceDeviceQueryDto params = AttenceDeviceQueryDto.builder()
                .tenantId(employeeDto.getTenantId())
                .buildingDeveloperId(Arrays.asList(employeeDto.getBuildingDeveloperId()))
                .buildingSiteId(Arrays.asList(employeeDto.getBuildingSiteId()))
                .deletedFlag(false).build();

        List<AttenceDeviceItemDto> devices = attenceDeviceService.findAttenceDeviceList(ContextHolder.builder().tenantId(employeeDto.getTenantId()).build(), params, new Limitable());
        List<EmployeeFeature> features = employeeFeatureRepo.loadEmployeeFeatureList(Arrays.asList(employeeDto.getIdCardNo()));

        List<IClockCommand> commands = new ArrayList<>();

        for(int i=0; i<devices.size(); i++){
            IClockCommand command = new IClockCommand();

            command.setId(getAccountIdByUUId() + "");
            command.setSn(devices.get(i).getSn());

            StringBuffer cmd = new StringBuffer();
            cmd.append("C:");
            cmd.append(command.getId() + ":DATA UPDATE USERINFO PIN=" + employeeDto.getCode() + "\t");
            cmd.append("Name=" + employeeDto.getName() + "\t");
            cmd.append("Passwd=\t");
            cmd.append("Card=\t");
            cmd.append("Grp=1\t");
            cmd.append("TZ=0000000000000000\t");
            cmd.append("Pri=0\t");
            command.setCommand(cmd.toString());

            commands.add(command);

            final int ic = i;

            features.stream().forEach((feature)->{
                if(StringUtils.isNotBlank(feature.getFace())){
                    List<IClockCommand> faceCmds = processCommand(feature.getFace(), devices.get(ic).getSn());

                    commands.addAll(faceCmds);
                }

                if(StringUtils.isNotBlank(feature.getFingerprint())){
                    List<IClockCommand> fingerprint = processCommand(feature.getFingerprint(), devices.get(ic).getSn());

                    commands.addAll(fingerprint);
                }
            });
        }

        iClockCommandRepo.addIClockCommandList(commands);
    }


    public static String getAccountIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        return machineId + String.format("%015d", hashCodeV);
    }

    protected List<IClockCommand> processCommand(String command, String sn){

        if(StringUtils.isBlank(command) || StringUtils.isBlank(sn)){
            return Collections.emptyList();
        }

        List<IClockCommand> list = new ArrayList<>(16);

        if(command.startsWith("FP")){
            String[] subCommands = command.split("\n");

            for (String subCommand : subCommands) {
                IClockCommand cmd = new IClockCommand();

                cmd.setId(getAccountIdByUUId());
                cmd.setSn(sn);
                cmd.setCommand( "C:" + cmd.getId() + ":DATA UPDATE FINGERTMP " + subCommand.substring(3));

                list.add(cmd);
            }
        }

        if(command.startsWith("FACE")){
            String[] subCommands = command.split("\n");

            for (String subCommand : subCommands) {
                IClockCommand cmd = new IClockCommand();

                cmd.setId(getAccountIdByUUId());
                cmd.setSn(sn);
                cmd.setCommand( "C:" +  cmd.getId() + ":DATA UPDATE FACE " + subCommand.substring(5));

                list.add(cmd);
            }
        }

        if(command.startsWith("USERPIC")){
            String[] subCommands = command.split("\n");

            for (String subCommand : subCommands) {
                IClockCommand cmd = new IClockCommand();

                cmd.setId(getAccountIdByUUId());
                cmd.setSn(sn);
                cmd.setCommand( "C:" + cmd.getId() + ":DATA UPDATE USERPIC " + subCommand.substring(8));

                list.add(cmd);
            }
        }


        return list;
    }

    protected void persistFingerprint(AttenceDevice attenceDevice, EmployeeDto emp, String command){

        if(!command.startsWith("FP")){
            return;
        }

        employeeFeatureRepo.updateEmployeeFingerprint(emp.getIdCardNo(), command);
    }

    protected void persistProcessFace(AttenceDevice attenceDevice, EmployeeDto emp, String command){

        if(!command.startsWith("FACE")){
            return;
        }

        employeeFeatureRepo.updateEmployeeFace(emp.getIdCardNo(), command);
    }

    protected void persistProcessPhoto(AttenceDevice attenceDevice, EmployeeDto emp, String command){

        if(!command.startsWith("FP")){
            return;
        }
    }
}
