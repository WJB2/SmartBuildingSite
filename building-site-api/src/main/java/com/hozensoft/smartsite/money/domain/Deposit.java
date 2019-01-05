package com.hozensoft.smartsite.money.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Deposit {

    private String id;

    private String tenantId;

    private String buildingDeveloperId;

    private BigDecimal deposit;

}
