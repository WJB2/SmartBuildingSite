package com.hozensoft.smartsite.attence.rest;

import com.hozensoft.smartsite.attence.dto.AttenceRuleItemDto;
import com.hozensoft.smartsite.attence.dto.AttenceRuleValueDto;
import com.hozensoft.smartsite.attence.service.AttenceRuleService;
import com.hozensoft.system.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building-site/attence-rule")
public class AttenceRuleRest {

    @Autowired
    private AttenceRuleService attenceRuleService;

    @PutMapping
    public @ResponseBody void saveAttenceRule(@RequestBody AttenceRuleValueDto attenceRuleDto){

        attenceRuleService.saveAttenceRule(ContextUtils.fetchContext(), attenceRuleDto);
    }

    @GetMapping(value="/list")
    public @ResponseBody
    List<AttenceRuleItemDto> findAttenceRuleList(@RequestParam("buildingDeveloperId") String buildingDeveloperId){

        return attenceRuleService.findAttenceRuleList(ContextUtils.fetchContext(), buildingDeveloperId);
    }
}
