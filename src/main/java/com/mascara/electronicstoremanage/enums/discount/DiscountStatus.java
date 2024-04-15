package com.mascara.electronicstoremanage.enums.discount;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:43 CH
 * Filename  : DiscountStatus
 */
public enum DiscountStatus {
    IS_APPLYING("IS_APPLYING", "Đang áp dụng"),
    EXPIRED("EXPIRED", "Hết hạn");
    private String key;
    private String display;

    DiscountStatus(String key, String display) {
        this.key = key;
        this.display = display;
    }
}
