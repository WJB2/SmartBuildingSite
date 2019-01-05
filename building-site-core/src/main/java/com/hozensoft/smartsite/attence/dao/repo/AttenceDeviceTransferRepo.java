package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.AttenceDeviceTransfer;
import org.apache.ibatis.annotations.Param;

public interface AttenceDeviceTransferRepo {

    int addAttenceDeviceTransfer(AttenceDeviceTransfer attenceDeviceTransfer);

    int editAttenceDeviceTransfer(AttenceDeviceTransfer attenceDeviceTransfer);

    int patchEditAttenceDeviceTransfer(AttenceDeviceTransfer attenceDeviceTransfer);

    int deleteAttenceDeviceTransferById(@Param("attenceDeviceTransferId") String attenceDeviceTransferId);

    AttenceDeviceTransfer loadAttenceDeviceTransferById(@Param("attenceDeviceTransferId") String attenceDeviceTransferId);
}
