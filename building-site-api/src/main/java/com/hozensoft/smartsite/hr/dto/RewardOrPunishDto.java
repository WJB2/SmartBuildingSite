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
@ApiModel(value = "奖惩对象")
public class RewardOrPunishDto {

    @ApiModelProperty(value="ID", required = false)
    private String id;

    @ApiModelProperty(value="租户ID", hidden = true)
    private String tenantId;

    @ApiModelProperty(value="开发商ID", hidden = true)
    private String buildingDeveloperId;

    private OrganizationAttrDto buildingDeveloper;

    @ApiModelProperty(value="工地ID", required = true)
    private String buildingSiteId;

    private BuildingSiteDto buildingSiteDto;

    @ApiModelProperty(value="奖惩类型", required = true)
    private RewardOrPunishEnum type;

    @ApiModelProperty(value="人员姓名", required = true)
    private String name;

    @ApiModelProperty(value="身份证号", required = true)
    private String idCardNo;

    @ApiModelProperty(value = "奖惩内容", required = true)
    private String content;

    @ApiModelProperty(value = "创建人ID", required = true)
    private String createdById;

    private StaffAttrDto createdBy;

    @ApiModelProperty(value = "创建人部门ID", required = true)
    private String createdOrgId;

    private OrganizationAttrDto createdOrg;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createdTime;

    @ApiModelProperty(value = "是否通过审查", required = true)
    private Boolean approved;

    @ApiModelProperty(value = "审查ID", required = true)
    private String auditById;

    private StaffAttrDto auditBy;

    @ApiModelProperty(value = "审查部门ID", required = true)
    private String auditOrgId;

    OrganizationAttrDto auditOrg;

    @ApiModelProperty(value = "审查时间", required = true)
    private Date auditTime;

    @ApiModelProperty(value = "审批内容", required = true)
    private String auditRemark;
}
