package com.hozensoft.smartsite.hr.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "�������ֵ����")
public class RewardOrPunishAuditValueDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="�⻧ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="��������", required = true)
    private String auditRemark;

    @ApiModelProperty(value="��ǰ��λID", hidden = true)
    private String currentPositionId;
}
