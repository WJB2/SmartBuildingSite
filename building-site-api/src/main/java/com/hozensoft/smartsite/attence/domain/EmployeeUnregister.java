package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 人员注销记录
 */
@Getter
@Setter
public class EmployeeUnregister {

    /**
     * ID
     */
    private String id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 开发商ID
     */
    private String buildingDeveloperId;

    /**
     * 工地ID
     */
    private String buildingSiteId;

    /**
     * 人员ID
     */
    private String employeeId;

    /**
     * 注销日期
     */
    private Date unregisterDate;

    /**
     * 创建人
     */
    private String createdById;

    /**
     * 创建组织机构
     */
    private String createdOrgId;

    /**
     * 创建时间
     */
    private Date createdTime;
}
