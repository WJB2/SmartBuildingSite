package com.hozensoft.smartsite.enumeration;

public enum RewardOrPunishEnum {

    REWARD(1,"����"),

    PUNISH(2,"�ͷ�");

    private Integer value;

    private String description;

    RewardOrPunishEnum(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static RewardOrPunishEnum valueOf(int i) {
        for (RewardOrPunishEnum status : RewardOrPunishEnum.values()) {
            if (i == status.getValue()) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknow Status's value " + i);
    }
}
