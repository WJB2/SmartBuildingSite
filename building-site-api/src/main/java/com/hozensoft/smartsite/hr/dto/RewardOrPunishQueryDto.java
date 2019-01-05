package com.hozensoft.smartsite.hr.dto;

import com.hozensoft.smartsite.enumeration.RewardOrPunishEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(value = "���Ͳ�ѯ����")
public class RewardOrPunishQueryDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="�⻧ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="������ID", hidden = true)
    private List<String> buildingDeveloperId;

    @ApiModelProperty(value="����ID", required = true)
    private List<String> buildingSiteId;

    @ApiModelProperty(value="��������", required = true)
    private RewardOrPunishEnum type;

    @ApiModelProperty(value="��Ա����", required = true)
    private String name;

    @ApiModelProperty(value="���֤��", required = true)
    private String idCardNo;

    @ApiModelProperty(value="��������", required = true)
    private String content;

    @ApiModelProperty(value = "������ID", required = true)
    private List<String> createdById;

    @ApiModelProperty(value = "�����˲���ID", required = true)
    private List<String> createdOrgId;

    @ApiModelProperty(value = "����ʱ��", required = true)
    private Date createdTime;

    @ApiModelProperty(value = "������ʼʱ��", required = true)
    private Date createdTimeBegin;

    @ApiModelProperty(value = "��������ʱ��", required = true)
    private Date createdTimeEnd;

    @ApiModelProperty(value = "�Ƿ�ͨ�����", required = true)
    private Boolean approved;

    @ApiModelProperty(value = "���ID", required = true)
    private List<String> auditById;

    @ApiModelProperty(value = "��鲿��ID", required = true)
    private List<String> auditOrgId;

    @ApiModelProperty(value = "���ʱ��", required = true)
    private Date auditTime;

    @ApiModelProperty(value = "��鿪ʼʱ��", required = true)
    private Date auditTimeBegin;

    @ApiModelProperty(value = "������ʱ��", required = true)
    private Date auditTimeEnd;

    @ApiModelProperty(value="�����ظ�����", required = true)
    private String auditRemark;

    private Boolean unauditedOnly;

    private Boolean auditedOnly;
}
