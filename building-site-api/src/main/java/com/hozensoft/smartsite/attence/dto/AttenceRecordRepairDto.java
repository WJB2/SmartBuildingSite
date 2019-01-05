package com.hozensoft.smartsite.attence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AttenceRecordRepairDto {

    private List<String> employeeIdList;

    private String buildingDeveloperId;

    private String buildingSiteId;

    private Date checkInTime;

    private Date breakCheckOutTime;

    private Date breakCheckInTime;

    private Date checkOutTime;
}
