package com.hozensoft.smartsite.enumeration;

/**
 * 考勤类型
 */
public enum CheckTypeEnum {

    CHECK_IN_NORMAL(1, "正常上班"),

    CHECK_IN_LATER(2, "上班迟到"),

    CHECK_OUT_NORMAL(3, "正常下班"),

    CHECK_OUT_EARLIER(4, "下班迟到");

    private Integer value;

    private String description;

    CheckTypeEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static CheckTypeEnum valueOf(int i) {
        for (CheckTypeEnum status : CheckTypeEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
