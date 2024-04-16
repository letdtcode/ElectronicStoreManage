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
}
