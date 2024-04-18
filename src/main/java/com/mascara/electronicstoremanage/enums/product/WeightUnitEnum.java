package com.mascara.electronicstoremanage.enums.product;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 12/04/2024
 * Time      : 6:05 CH
 * Filename  : WeightUnitEnum
 */
public enum WeightUnitEnum {
    KG("KG", "kg"),

    G("G", "gam");

    private String key;
    private String display;

    WeightUnitEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }
}
