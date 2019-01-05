package com.hozensoft.smartsite.attence.dao.query;

import com.hozensoft.smartsite.attence.dto.AttenceRuleItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttenceRuleQuery {

    List<AttenceRuleItemDto> findAttenceRuleList(@Param("tenantId") String tenantId,
                                                 @Param("buildingDeveloperId") String buildingDeveloperId);
}
