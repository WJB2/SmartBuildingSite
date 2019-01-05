package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeValueDto {

    private String id;

    private String tenantId;

    private String buildingDeveloperId;

    private String buildingSiteId;

    private String code;

    private String name;

    private String idCardNo;

    private String bank;

    private String bankNo;

    private String gender;

    private Date birthday;

    private String address;

    private String mobile;

    private String workType;

    private Date registerTime;

    private String currentPositionId;
}
