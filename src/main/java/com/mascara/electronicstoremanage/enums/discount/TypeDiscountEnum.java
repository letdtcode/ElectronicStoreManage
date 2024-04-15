package com.mascara.electronicstoremanage.enums.discount;

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
}
