package com.mascara.electronicstoremanage.enums.order;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:56 CH
 * Filename  : ModeOfDeliveryEnum
 */
public enum ModeOfDeliveryEnum {
    DIRECT_SALE("DIRECT_SALE", "Trực tiếp"),

    OTHER("OTHER", "Khác");

    private final String key;
    private final String display;

    ModeOfDeliveryEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static ModeOfDeliveryEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Trực tiếp":
                return ModeOfDeliveryEnum.DIRECT_SALE;
            case "Khác":
                return ModeOfDeliveryEnum.OTHER;
            default:
                return null;
        }
    }
}
