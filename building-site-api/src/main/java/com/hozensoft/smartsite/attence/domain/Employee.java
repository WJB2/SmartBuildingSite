package com.hozensoft.smartsite.attence.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee {

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
     * 人员工号
     */
    private String code;

    /**
     * 姓名
     */
    private String name;

    /**
     * 姓名简拼
     */
    private String namePinyin;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 银行卡号
     */
    private String bankNo;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系手机号
     */
    private String mobile;

    /**
     * 工种
     */
    private String workType;

    /**
     * 入职日期
     */
    private Date registerTime;

    /**
     * 是否注销
     */
    private Boolean unregistered;

    /**
     * 注销日期
     */
    private Date unregisterTime;

    /**
     * 指纹登记
     */
    private Boolean fingerprintRegistered;

    /**
     * 人脸登记
     */
    private Boolean faceRegistered;

    private Boolean deletedFlag;

    private String createdById;

    private String createdOrgId;

    private Date createdTime;

    private String updatedById;

    private String updatedOrgId;

    private Date updatedTime;

    private String deletedById;

    private String deletedOrgId;

    private Date deletedTime;
}
