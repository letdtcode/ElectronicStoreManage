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
    NOT_APPLY("NOT_APPLY", "Không áp dụng");
    private String key;
    private String display;

    DiscountStatus(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static DiscountStatus getEnumByDisplay(String display) {
        switch (display) {
            case "Đang áp dụng":
                return DiscountStatus.IS_APPLYING;
            case "Không áp dụng":
                return DiscountStatus.NOT_APPLY;
            default:
                return null;
        }
    }
}
