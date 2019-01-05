package com.hozensoft.smartsite.attence.service;

import com.hozensoft.smartsite.attence.domain.AttenceRule;
import com.hozensoft.smartsite.attence.dto.AttenceRuleItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceRuleValueDto;
import com.hozensoft.system.utils.bean.ContextHolder;

import java.util.List;

public interface AttenceRuleService {

    void saveAttenceRule(ContextHolder holder, AttenceRuleValueDto attenceRuleDto);

    AttenceRule loadAttenceRuleByBuildingSiteId(ContextHolder holder, String buildingDeveloperId, String buildingSiteId);

    List<AttenceRuleItemDto> findAttenceRuleList(ContextHolder holder,String buildingDeveloperId);
}
