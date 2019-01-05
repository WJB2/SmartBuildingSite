package com.hozensoft.smartsite.attence.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttenceDeviceTransferQueryDto {

    private String tenantId;

    private List<String> buildingDeveloperId;

    private List<String> buildingSiteId;

    /**
     * 逻辑删除标记
     */
    private Boolean deletedFlag;
}
