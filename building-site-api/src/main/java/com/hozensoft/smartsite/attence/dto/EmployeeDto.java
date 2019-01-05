package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.base.dto.BuildingDeveloperDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteDto;
import com.hozensoft.system.core.dto.OrganizationAttrDto;
import com.hozensoft.system.core.dto.StaffAttrDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EmployeeDto implements Serializable {

    private String id;

    private String tenantId;

    private String buildingDeveloperId;

    private BuildingDeveloperDto buildingDeveloper;

    private String buildingSiteId;

    private BuildingSiteDto buildingSite;

    private String code;

    private String name;

    private String namePinyin;

    private String idCardNo;

    private String bank;

    private String bankNo;

    private String gender;

    private Date birthday;

    private String address;

    private String mobile;

    private String workType;

    private Date registerTime;

    private Boolean unregistered;

    private Date unregisterTime;

    private Boolean fingerprintRegistered;

    private Boolean faceRegistered;

    private Boolean deletedFlag;

    private String createdById;

    private StaffAttrDto createdBy;

    private String createdOrgId;

    private OrganizationAttrDto createdOrg;

    private Date createdTime;

    private String updatedById;

    private StaffAttrDto updatedBy;

    private String updatedOrgId;

    private OrganizationAttrDto updatedOrg;

    private Date updatedTime;

    private String deletedById;

    private StaffAttrDto deletedBy;

    private String deletedOrgId;

    private OrganizationAttrDto deletedOrg;

    private Date deletedTime;
}
