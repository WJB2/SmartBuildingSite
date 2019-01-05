package com.hozensoft.smartsite.attence.transfermer;

import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceValueDto;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class AttenceDeviceTransformer {

    public static AttenceDevice transformAttenceDeviceAddDtoToDomain(AttenceDeviceValueDto attenceDeviceDto) {

        AttenceDevice attenceDevice = new AttenceDevice();

        BeanUtils.copyProperties(attenceDeviceDto, attenceDevice);

        if(StringUtils.isBlank(attenceDevice.getId())){
            attenceDevice.setId(IdGen.generate());
        }

        if(attenceDevice.getDeletedFlag()==null){
            attenceDevice.setDeletedFlag(false);
        }

        return attenceDevice;
    }

    public static AttenceDevice transformAttenceDeviceEditDtoToDomain(AttenceDeviceValueDto attenceDeviceDto) {

        AttenceDevice attenceDevice = new AttenceDevice();

        BeanUtils.copyProperties(attenceDeviceDto, attenceDevice);

        return attenceDevice;
    }

}