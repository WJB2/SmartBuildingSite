package com.hozensoft.smartsite.hr.domain;

import com.hozensoft.smartsite.enumeration.RewardOrPunishEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RewardOrPunish {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 上报开发商ID
     */
    private String buildingDeveloperId;

    /**
     * 上报工地ID
     */
    private String buildingSiteId;

    /**
     * 奖励或者惩罚枚举变量
     */
    private RewardOrPunishEnum type;

    /**
     * 奖惩人
     */
    private String name;

    /**
     * 奖惩人身份证号
     */
    private String idCardNo;

    /**
     * 奖惩内容
     */
    private String content;

    /**
     * 创建人
     */
    private String createdById;

    /**
     * 创建人所在部门
     */
    private String createdOrgId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 是否审核通过
     */
    private Boolean approved;

    /**
     * 审核人
     */
    private String auditById;

    /**
     * 审核人所在部门
     */
    private String auditOrgId;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核意见
     */
    private String auditRemark;

}
