package com.hozensoft.smartsite.base.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BuildingSiteQueryDto {

    /**
     * ID
     */
    private List<String> id;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 开发商ID
     */
    private List<String> buildingDeveloperId;

    /**
     * 管理员用户ID
     */
    private List<String> adminStaffId;

    private String buildingSiteText;

    private Boolean deletedFlag;

}
