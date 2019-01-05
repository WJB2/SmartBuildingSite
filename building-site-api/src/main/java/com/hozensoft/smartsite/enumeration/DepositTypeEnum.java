package com.hozensoft.smartsite.enumeration;

public enum DepositTypeEnum {

    DEPOSIT(1, "存款"),

    RETURN(2, "退还"),

    USE(3, "发放工资");

    private Integer value;

    private String description;

    DepositTypeEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static DepositTypeEnum valueOf(int i) {
        for (DepositTypeEnum status : DepositTypeEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
