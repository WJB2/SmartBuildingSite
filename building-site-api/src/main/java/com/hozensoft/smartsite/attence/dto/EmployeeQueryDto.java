package com.hozensoft.smartsite.attence.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeQueryDto {

    private String tenantId;

    private List<String> id;

    private List<String> code;

    private List<String> idCardNo;

    private List<String> bankCardNo;

    private List<String> buildingDeveloperId;

    private List<String> buildingSiteId;

    private Boolean unregistered;

    private Boolean deletedFlag;

    private String employeeText;
}
