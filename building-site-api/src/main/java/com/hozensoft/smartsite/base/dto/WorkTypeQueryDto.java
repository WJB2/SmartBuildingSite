package com.hozensoft.smartsite.base.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkTypeQueryDto {

    private String tenantId;

    private List<String> buildingDeveloperId;

    private List<String> buildingSiteId;

    private String workTypeText;
}
