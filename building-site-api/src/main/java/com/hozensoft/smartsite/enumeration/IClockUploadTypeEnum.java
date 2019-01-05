package com.hozensoft.smartsite.enumeration;

public enum IClockUploadTypeEnum {

    ATTLOG(1, "考勤记录"),

    OPERLOG(2, "操作日志"),

    ATTPHOTO(3, "考勤照片"),

    BIODATA(4, "一体化模板");

    private int value;

    private String description;

    IClockUploadTypeEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static IClockUploadTypeEnum valueOf(int i) {
        for (IClockUploadTypeEnum status : IClockUploadTypeEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
