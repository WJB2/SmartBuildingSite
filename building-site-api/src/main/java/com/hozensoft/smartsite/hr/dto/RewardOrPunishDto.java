package com.hozensoft.smartsite.hr.dto;

import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.smartsite.enumeration.RewardOrPunishEnum;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value = "���Ͷ���")
public class RewardOrPunishDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="�⻧ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="������ID", hidden = true)
    private String buildingDeveloperId;

    private OrganizationAttrDto buildingDeveloper;

    @ApiModelProperty(value="����ID", required = true)
    private String buildingSiteId;

    private BuildingSiteDto buildingSiteDto;

    @ApiModelProperty(value="��������", required = true)
    private RewardOrPunishEnum type;

    @ApiModelProperty(value="��Ա����", required = true)
    private String name;

    @ApiModelProperty(value="���֤��", required = true)
    private String idCardNo;

    @ApiModelProperty(value = "��������", required = true)
    private String content;

    @ApiModelProperty(value = "������ID", required = true)
    private String createdById;

    private StaffAttrDto createdBy;

    @ApiModelProperty(value = "�����˲���ID", required = true)
    private String createdOrgId;

    private OrganizationAttrDto createdOrg;

    @ApiModelProperty(value = "����ʱ��", required = true)
    private Date createdTime;

    @ApiModelProperty(value = "�Ƿ�ͨ�����", required = true)
    private Boolean approved;

    @ApiModelProperty(value = "���ID", required = true)
    private String auditById;

    private StaffAttrDto auditBy;

    @ApiModelProperty(value = "��鲿��ID", required = true)
    private String auditOrgId;

    OrganizationAttrDto auditOrg;

    @ApiModelProperty(value = "���ʱ��", required = true)
    private Date auditTime;

    @ApiModelProperty(value = "��������", required = true)
    private String auditRemark;
}
