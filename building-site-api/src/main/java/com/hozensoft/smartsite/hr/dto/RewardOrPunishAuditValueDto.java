package com.hozensoft.smartsite.hr.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "奖惩审查值对象")
public class RewardOrPunishAuditValueDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="租户ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="审批内容", required = true)
    private String auditRemark;

    @ApiModelProperty(value="当前岗位ID", hidden = true)
    private String currentPositionId;
}
