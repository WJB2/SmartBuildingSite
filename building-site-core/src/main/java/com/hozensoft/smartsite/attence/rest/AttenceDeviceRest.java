package com.hozensoft.smartsite.attence.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceValueDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceService;
import com.hozensoft.smartsite.iclock.rest.IClockRest;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/attence-device")
public class AttenceDeviceRest {

    @Autowired
    private AttenceDeviceService attenceDeviceService;

    @PostMapping
    public @ResponseBody AttenceDeviceDto addAttenceDevice(@RequestBody AttenceDeviceValueDto attenceDeviceDto){

        attenceDeviceDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return attenceDeviceService.addAttenceDevice(ContextUtils.fetchContext(), attenceDeviceDto);
    }

    @PutMapping("/{attenceDeviceId}")
    public @ResponseBody AttenceDeviceDto editAttenceDevice(@PathVariable("attenceDeviceId")String attenceDeviceId,
                                                                    @RequestBody AttenceDeviceValueDto attenceDeviceDto){

        attenceDeviceDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return attenceDeviceService.editAttenceDevice(ContextUtils.fetchContext(), attenceDeviceDto);
    }

    @DeleteMapping("/{attenceDeviceId}")
    public void deleteAttenceDeviceById(@PathVariable("attenceDeviceId")String attenceDeviceId){

        attenceDeviceService.deleteAttenceDeviceById(ContextUtils.fetchContext(), attenceDeviceId);
    }

    @GetMapping("/{attenceDeviceId}")
    public @ResponseBody AttenceDeviceDto findAttenceDeviceById(@PathVariable("attenceDeviceId")String attenceDeviceId){

        return attenceDeviceService.findAttenceDeviceById(ContextUtils.fetchContext(), attenceDeviceId);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<AttenceDeviceItemDto> findAttenceDeviceList(HttpServletRequest request, AttenceDeviceQueryDto params, Limitable limitable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        List<AttenceDeviceItemDto> list = attenceDeviceService.findAttenceDeviceList(ContextUtils.fetchContext(), params, limitable);

        final Map<String, Date> onlineDeviceHash = (Map<String, Date>)request.getServletContext().getAttribute(IClockRest.KEY);

        list.stream().forEach((device)->{
            Date date = onlineDeviceHash.get(device.getSn());

            if(date!=null&&(new Date().getTime()-date.getTime())<10*60*1000){
                device.setOnlineFlag(true);
            }else{
                device.setOnlineFlag(false);
            }
        });

        return list;
    }

    @GetMapping("/page")
    public @ResponseBody
    PageInfo<AttenceDeviceItemDto> findAttenceDevicePage(HttpServletRequest request, AttenceDeviceQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        final Map<String, Date> onlineDeviceHash = (Map<String, Date>)request.getServletContext().getAttribute(IClockRest.KEY);

        PageInfo<AttenceDeviceItemDto> page = attenceDeviceService.findAttenceDevicePage(ContextUtils.fetchContext(), params, pageable);

        if(onlineDeviceHash!=null){
            page.getList().stream().forEach((device)->{


                Date date = onlineDeviceHash.get(device.getSn());

                if(date!=null&&(new Date().getTime()-date.getTime())<10*60*1000){
                    device.setOnlineFlag(true);
                }else{
                    device.setOnlineFlag(false);
                }
            });
        }

        return page;
    }
}
