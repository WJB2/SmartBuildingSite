package com.hozensoft.smartsite.attence.rest;

import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferQueryDto;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferValueDto;
import com.hozensoft.smartsite.attence.service.AttenceDeviceTransferService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(value="开发商管理接口")
@RestController
@RequestMapping("/api/building-site/attence-device-transfer")
public class AttenceDeviceTransferRest {

    @Autowired
    private AttenceDeviceTransferService attenceDeviceTransferService;

    @PostMapping
    public @ResponseBody
    AttenceDeviceTransferDto addAttenceDeviceTransfer(@RequestBody AttenceDeviceTransferValueDto attenceDeviceTransferDto){

        attenceDeviceTransferDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return attenceDeviceTransferService.addAttenceDeviceTransfer(ContextUtils.fetchContext(),attenceDeviceTransferDto);
    }

    @PutMapping("/{attenceDeviceTransferId}")
    public @ResponseBody
    AttenceDeviceTransferDto editAttenceDeviceTransfer(@PathVariable("attenceDeviceTransferId")String attenceDeviceTransferId,
                                                       @RequestBody AttenceDeviceTransferValueDto attenceDeviceTransferDto){

        return attenceDeviceTransferService.editAttenceDeviceTransfer(ContextUtils.fetchContext(), attenceDeviceTransferDto);
    }

    @DeleteMapping("/{attenceDeviceTransferId}")
    public void deleteAttenceDeviceTransferById(@PathVariable("attenceDeviceTransferId")String attenceDeviceTransferId){

        attenceDeviceTransferService.deleteAttenceDeviceTransferById(ContextUtils.fetchContext(),attenceDeviceTransferId);
    }

    @GetMapping("/{attenceDeviceTransferId}")
    public @ResponseBody
    AttenceDeviceTransferDto findAttenceDeviceTransferById(@PathVariable("attenceDeviceTransferId")String attenceDeviceTransferId){

        return attenceDeviceTransferService.findAttenceDeviceTransferById(ContextUtils.fetchContext(),attenceDeviceTransferId);
    }

    @GetMapping("/list")
    public @ResponseBody
    List<AttenceDeviceTransferItemDto> findAttenceDeviceTransferList(AttenceDeviceTransferQueryDto params, Limitable limitable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        return attenceDeviceTransferService.findAttenceDeviceTransferList(ContextUtils.fetchContext(),params, limitable);
    }

    @GetMapping("/page")
    public @ResponseBody
    List<AttenceDeviceTransferItemDto> findAttenceDeviceTransferPage(AttenceDeviceTransferQueryDto params, Pageable pageable){
        if(StringUtils.isBlank(params.getTenantId())){
            params.setTenantId(ContextUtils.fetchContext().getTenantId());
        }

        if(CollectionUtils.isEmpty(params.getBuildingDeveloperId())){
            params.setBuildingDeveloperId(Arrays.asList(ContextUtils.getCurrentOrganization().getId()));
        }

        return attenceDeviceTransferService.findAttenceDeviceTransferPage(ContextUtils.fetchContext(),params, pageable);
    }
}
