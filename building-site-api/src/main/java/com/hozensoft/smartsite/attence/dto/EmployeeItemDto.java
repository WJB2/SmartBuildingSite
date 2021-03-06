package com.hozensoft.smartsite.attence.dto;

import com.hozensoft.smartsite.base.dto.BuildingDeveloperItemDto;
import com.hozensoft.smartsite.base.dto.BuildingSiteItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeItemDto {

    private String id;

    private String tenantId;

    private String buildingDeveloperId;

    private BuildingDeveloperItemDto buildingDeveloper;

    private String buildingSiteId;

    private BuildingSiteItemDto buildingSite;

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

    private Boolean deletedFlag;

    private Boolean fingerprintRegistered;

    private Boolean faceRegistered;

}
