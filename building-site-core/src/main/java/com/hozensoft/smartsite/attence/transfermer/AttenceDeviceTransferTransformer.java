package com.hozensoft.smartsite.attence.transfermer;

import com.hozensoft.smartsite.attence.domain.AttenceDeviceTransfer;
import com.hozensoft.smartsite.attence.dto.AttenceDeviceTransferValueDto;
import org.springframework.beans.BeanUtils;

public class AttenceDeviceTransferTransformer {
    public static AttenceDeviceTransfer transformAttenceDeviceTransferAddToDomain(AttenceDeviceTransferValueDto employee) {

        AttenceDeviceTransfer domain = new AttenceDeviceTransfer();

        BeanUtils.copyProperties(employee, domain);


        return domain;
    }

    public static AttenceDeviceTransfer transforAttenceDeviceTransferEditToDomain(AttenceDeviceTransferValueDto employee) {

        AttenceDeviceTransfer domain = new AttenceDeviceTransfer();

        BeanUtils.copyProperties(employee, domain);

        return domain;
    }

    public static AttenceDeviceTransfer mergebAttenceDeviceTransferEditToDomain(AttenceDeviceTransferValueDto employee, AttenceDeviceTransfer domain) {

        BeanUtils.copyProperties(employee, domain);

        return domain;
    }

}