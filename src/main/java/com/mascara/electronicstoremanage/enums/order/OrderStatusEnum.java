package com.mascara.electronicstoremanage.enums.order;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 11:00 CH
 * Filename  : OrderStatus
 */
public enum OrderStatusEnum {
    PENDING("PENDING", "Chờ thanh toán"),
    PAID("PAID", "Đã thanh toán"),
    CANCELED("CANCELED", "Đã hủy");

    private String key;
    private String display;

    OrderStatusEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public static OrderStatusEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Chờ thanh toán":
                return OrderStatusEnum.PENDING;
            case "Đã thanh toán":
                return OrderStatusEnum.PAID;
            case "Đã hủy":
                return OrderStatusEnum.CANCELED;
            default:
                return null;
        }
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }
}
