package com.mascara.electronicstoremanage.enums.customer;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 20/04/2024
 * Time      : 7:42 CH
 * Filename  : CustomerStatusEnum
 */
public enum CustomerStatusEnum {
    ACTIVE("ACTIVE", "Hoạt động"),
    INACTIVE("INACTIVE", "Ngừng hoạt động");

    private final String key;
    private final String display;

    CustomerStatusEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static CustomerStatusEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Hoạt động":
                return CustomerStatusEnum.ACTIVE;
            case "Ngừng hoạt động":
                return CustomerStatusEnum.INACTIVE;
            default:
                return null;
        }
    }
}
