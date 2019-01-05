package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.AttenceDevice;
import org.apache.ibatis.annotations.Param;

public interface AttenceDeviceRepo {

    int addAttenceDevice(AttenceDevice attenceDevice);

    int editAttenceDevice(AttenceDevice attenceDevice);

    int patchEditAttenceDevice(AttenceDevice attenceDevice);

    int deleteAttenceDeviceById(@Param("tenantId") String tenantId, @Param("attenceDeviceId")String attenceDeviceId);

    AttenceDevice loadAttenceDeviceById(@Param("tenantId") String tenantId, @Param("attenceDeviceId")String attenceDeviceId);

    AttenceDevice loadAttenceDeviceBySn(@Param("attenceDeviceSn")String attenceDeviceSn);
}
