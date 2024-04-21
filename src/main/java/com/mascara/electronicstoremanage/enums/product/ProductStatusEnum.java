package com.mascara.electronicstoremanage.enums.product;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:21 CH
 * Filename  : ProductStatusEnum
 */
public enum ProductStatusEnum {

    ON_BUSINESS("ON_BUSINESS", "Đang kinh doanh"),

    STOP_BUSINESS("STOP_BUSINESS", "Ngừng kinh doanh");
    private final String key;
    private final String display;

    ProductStatusEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static ProductStatusEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Đang kinh doanh":
                return ProductStatusEnum.ON_BUSINESS;
            case "Ngừng kinh doanh":
                return ProductStatusEnum.STOP_BUSINESS;
            default:
                return null;
        }
    }
}
