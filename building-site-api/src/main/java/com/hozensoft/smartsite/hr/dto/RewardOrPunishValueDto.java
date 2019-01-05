package com.hozensoft.smartsite.hr.dto;

import com.hozensoft.smartsite.enumeration.RewardOrPunishEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value="������Ϣֵ����")
public class RewardOrPunishValueDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="�⻧ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="������ID", hidden = true)
    private String buildingDeveloperId;

    @ApiModelProperty(value="����ID", required = true)
    private String buildingSiteId;

    @ApiModelProperty(value="��������", required = true)
    private RewardOrPunishEnum type;

    @ApiModelProperty(value="��Ա����", required = true)
    private String name;

    @ApiModelProperty(value="���֤��", required = true)
    private String idCardNo;

    @ApiModelProperty(value="��������", required = true)
    private String content;

    @ApiModelProperty(value="��ǰ��λID", hidden = true)
    private String currentPositionId;

    @ApiModelProperty(value = "�Ƿ�ͨ�����", required = true)
    private Boolean approved;

    @ApiModelProperty(value="��������", required = true)
    private String auditRemark;
}
