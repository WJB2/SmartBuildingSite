package com.hozensoft.smartsite.enumeration;


public enum ClockTypeEnum {

    MANNUAL(1, "人工"),

    FACE(2, "刷脸"),

    FINGERPRINT(3, "指纹"),

    CARD(4, "身份证");

    private int value;

    private String description;

    ClockTypeEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ClockTypeEnum valueOf(int i) {
        for (ClockTypeEnum status : ClockTypeEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
