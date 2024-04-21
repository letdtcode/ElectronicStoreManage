package com.mascara.electronicstoremanage.enums.general;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:39 CH
 * Filename  : SexEnum
 */
public enum SexEnum {
    MALE("MALE", "Nam"),
    FEMALE("FEMALE", "Nữ");
    private final String key;
    private final String display;

    SexEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static SexEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Nam":
                return SexEnum.MALE;
            case "Nữ":
                return SexEnum.FEMALE;
            default:
                return null;
        }
    }
}
