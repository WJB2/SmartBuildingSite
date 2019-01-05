package com.hozensoft.smartsite.attence.service.impl;

import com.hozensoft.smartsite.attence.dao.query.AttenceRuleQuery;
import com.hozensoft.smartsite.attence.dao.repo.AttenceRuleRepo;
import com.hozensoft.smartsite.attence.domain.AttenceRule;
import com.hozensoft.smartsite.attence.dto.AttenceRuleItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceRuleValueDto;
import com.hozensoft.smartsite.attence.service.AttenceRuleService;
import com.hozensoft.system.utils.bean.ContextHolder;
import com.hozensoft.utils.persistent.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttenceRuleServiceImpl implements AttenceRuleService {

    @Autowired
    private AttenceRuleRepo attenceRuleRepo;

    @Autowired
    private AttenceRuleQuery attenceRuleQuery;

    @Override
    @Transactional
    public void saveAttenceRule(ContextHolder holder, AttenceRuleValueDto attenceRuleDto) {

        AttenceRule attenceRule = attenceRuleRepo.loadAttenceRuleByBuildingSiteId(holder.getTenantId(), attenceRuleDto.getBuildingDeveloperId(), attenceRuleDto.getBuildingSiteId());

        if(attenceRule == null){
            attenceRule = new AttenceRule();

            BeanUtils.copyProperties(attenceRuleDto, attenceRule);

            attenceRule.setId(IdGen.generate());
            attenceRule.setTenantId(holder.getTenantId());

            attenceRuleRepo.addAttenceRule(attenceRule);
        }else{
            attenceRule.setBreakEnabled(attenceRuleDto.getBreakEnabled());
            attenceRule.setBeginTime(attenceRuleDto.getBeginTime());
            attenceRule.setBreakBeginTime(attenceRuleDto.getBreakBeginTime());
            attenceRule.setBreakEndTime(attenceRuleDto.getBreakEndTime());
            attenceRule.setEndTime(attenceRuleDto.getEndTime());

            attenceRuleRepo.editAttenceRule(attenceRule);
        }
    }

    @Override
    public AttenceRule loadAttenceRuleByBuildingSiteId(ContextHolder holder,String buildingDeveloperId, String buildingSiteId) {

        return attenceRuleRepo.loadAttenceRuleByBuildingSiteId(holder.getTenantId(), buildingDeveloperId, buildingSiteId);
    }

    @Override
    public List<AttenceRuleItemDto> findAttenceRuleList(ContextHolder holder,String buildingDeveloperId) {

        return attenceRuleQuery.findAttenceRuleList(holder.getTenantId(), buildingDeveloperId);
    }
}
