package com.hozensoft.smartsite.attence.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttenceDeviceQueryDto {
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
    private List<String> buildingDeveloperId;

    /**
     * 工地ID
     */
    private List<String> buildingSiteId;

    /**
     * 设备唯一编码
     */
    private String sn;

    /**
     * 删除标记
     * */
    private Boolean deletedFlag;

    private String attenceDeviceText;
}
