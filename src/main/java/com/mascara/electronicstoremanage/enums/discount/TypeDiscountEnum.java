package com.mascara.electronicstoremanage.enums.discount;

import com.mascara.electronicstoremanage.enums.customer.CustomerStatusEnum;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:42 CH
 * Filename  : TypeDiscount
 */
public enum TypeDiscountEnum {
    PERCENT("PERCENT", "Phần trăm"),
    CASH("CASH", "Tiền mặt");
    private String key;
    private String display;

    TypeDiscountEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static TypeDiscountEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Phần trăm":
                return TypeDiscountEnum.PERCENT;
            case "Tiền mặt":
                return TypeDiscountEnum.CASH;
            default:
                return null;
        }
    }
}
