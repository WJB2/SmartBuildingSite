package com.hozensoft.smartsite.attence.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttenceRecordQueryDto {

    private String tenantId;

    private List<String> employeeId;

    private List<String> buildingDeveloperId;

    private List<String> buildingSiteId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
