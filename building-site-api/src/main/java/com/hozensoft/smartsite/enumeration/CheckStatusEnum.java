package com.hozensoft.smartsite.enumeration;

public enum CheckStatusEnum {

    NORAML(1, "考勤正常"),

    ABNORMAL(2, "考勤异常"),

    LOST(3, "缺卡");

    private Integer value;

    private String description;

    CheckStatusEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static CheckStatusEnum valueOf(int i) {
        for (CheckStatusEnum status : CheckStatusEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
