package com.mascara.electronicstoremanage.enums.product;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:21 CH
 * Filename  : ProductStatusEnum
 */
public enum ProductStatusEnum {

    ON_BUSINESS("ON_BUSINESS","Đang kinh doanh"),

    STOP_BUSINESS("STOP_BUSINESS","Ngừng kinh doanh");
    private final String key;
    private final String showView;

     ProductStatusEnum(String key, String showView) {
        this.key = key;
        this.showView = showView;
    }

    public String getKey() {
        return key;
    }

    public String getShowView() {
        return showView;
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
