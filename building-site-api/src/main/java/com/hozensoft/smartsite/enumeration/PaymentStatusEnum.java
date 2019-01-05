package com.hozensoft.smartsite.enumeration;

public enum PaymentStatusEnum {

    UNSUBMIT(1, "未提交"),

    SUBMITTED(2, "待发放"),

    PAID(3, "已发放"),

    PROCESSING(4, "银行处理中");

    private Integer value;

    private String description;

    PaymentStatusEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatusEnum valueOf(int i) {
        for (PaymentStatusEnum status : PaymentStatusEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
