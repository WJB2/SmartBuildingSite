package com.hozensoft.smartsite.hr.rest;

import com.github.pagehelper.PageInfo;
import com.hozensoft.smartsite.hr.dto.*;
import com.hozensoft.smartsite.hr.service.RewardOrPunishService;
import com.hozensoft.struct.pagehelper.Limitable;
import com.hozensoft.struct.pagehelper.Pageable;
import com.hozensoft.system.utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smart-site/reward-or-punish")
public class RewardOrPunishRest {

    @Autowired
    private RewardOrPunishService rewardOrPunishService;

    @PostMapping
    public RewardOrPunishDto addRewardOrPunish(@RequestBody RewardOrPunishValueDto rewardOrPunishDto){

        rewardOrPunishDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return rewardOrPunishService.addRewardOrPunish(ContextUtils.fetchContext(), rewardOrPunishDto);
    }

    @PutMapping(value="/{rewardOrPunishId}")
    public RewardOrPunishDto editRewardOrPunish(@PathVariable("rewardOrPunishId")String rewardOrPunishId,
                                                @RequestBody RewardOrPunishValueDto rewardOrPunishDto){

        rewardOrPunishDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());
        rewardOrPunishDto.setTenantId(ContextUtils.fetchContext().getTenantId());
        rewardOrPunishDto.setBuildingDeveloperId(ContextUtils.getCurrentOrganization().getId());
        rewardOrPunishDto.setBuildingSiteId(ContextUtils.getCurrentOrganization().getId());

        return rewardOrPunishService.editRewardOrPunish(ContextUtils.fetchContext(), rewardOrPunishDto);
    }

    @PutMapping(value="/audit/{rewardOrPunishId}")
    public RewardOrPunishDto auditRewardOrPunish(@PathVariable("rewardOrPunishId")String rewardOrPunishId,
                                                 @RequestBody RewardOrPunishAuditValueDto rewardOrPunishAduditDto){

        rewardOrPunishAduditDto.setCurrentPositionId(ContextUtils.getCurrentPosition().getId());

        return rewardOrPunishService.auditRewardOrPunish(ContextUtils.fetchContext(), rewardOrPunishAduditDto);
    }

    @DeleteMapping(value="/{rewardOrPunishId}")
    public @ResponseBody void deleteRewardOrPunishById(@PathVariable("rewardOrPunishId")String rewardOrPunishId){

        rewardOrPunishService.deleteRewardOrPunish(ContextUtils.fetchContext(), rewardOrPunishId);
    }

    @GetMapping(value="/{rewardOrPunishId}")
    public @ResponseBody RewardOrPunishDto getRewardOrPunishById(@PathVariable("rewardOrPunishId")String rewardOrPunishId){

        return rewardOrPunishService.findRewardOrPunishById(ContextUtils.fetchContext(), rewardOrPunishId);
    }

    @GetMapping(value="/list")
    public @ResponseBody
    List<RewardOrPunishItemDto> getRewardOrPunishList(RewardOrPunishQueryDto params, Limitable limitable){

        return rewardOrPunishService.findRewardOrPunishList(ContextUtils.fetchContext(), params, limitable);
    }

    @GetMapping(value="/page")
    public @ResponseBody
    PageInfo<RewardOrPunishItemDto> getRewardOrPunishPage(RewardOrPunishQueryDto params, Pageable pageable){

        return rewardOrPunishService.findRewardOrPunishPage(ContextUtils.fetchContext(), params, pageable);
    }

    @GetMapping(value="/not-audited/page")
    public @ResponseBody
    PageInfo<RewardOrPunishItemDto> getUnapprovedRewardOrPunishPage(RewardOrPunishQueryDto params, Pageable pageable){

        return rewardOrPunishService.findRewardOrPunishPage(ContextUtils.fetchContext(), params, pageable);
    }
}
