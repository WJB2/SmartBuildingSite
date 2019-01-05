package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.AttenceRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface AttenceRecordRepo {

    int addAttenceRecord(AttenceRecord record);

    int editAttenceRecord(AttenceRecord record);

    AttenceRecord loadAttenceByEmployeeAndCheckDate(@Param("tenantId") String tenantId,
                                                    @Param("checkDate") Date checkDate,
                                                    @Param("buildingDeveloperId") String buildingDeveloperId,
                                                    @Param("buildingSiteId") String buildingSiteId,
                                                    @Param("employeeId") String employeeId);
}
