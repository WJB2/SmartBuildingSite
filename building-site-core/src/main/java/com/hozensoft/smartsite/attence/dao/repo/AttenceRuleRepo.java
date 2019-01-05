package com.hozensoft.smartsite.attence.dao.repo;

import com.hozensoft.smartsite.attence.domain.AttenceRule;
import org.apache.ibatis.annotations.Param;

public interface AttenceRuleRepo {

    int addAttenceRule(AttenceRule attenceRule);

    int editAttenceRule(AttenceRule attenceRule);

    AttenceRule loadAttenceRuleByBuildingSiteId(@Param("tenantId") String tenantId,
                                    @Param("buildingDeveloperId") String buildingDeveloperId,
                                    @Param("buildingSiteId") String buildingSiteId);
}
