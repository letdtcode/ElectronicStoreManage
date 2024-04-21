package com.mascara.electronicstoremanage.enums.order;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:56 CH
 * Filename  : ModeOfPaymentEnum
 */
public enum ModeOfPaymentEnum {
    CASH("CASH", "Tiền mặt"),
    CARD_SWIPE("CARD_SWIPE", "Quẹt thẻ"),
    BANK_TRANSFER("BANK_TRANSFER", "Chuyển khoản");

    private final String key;
    private final String display;

    ModeOfPaymentEnum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static ModeOfPaymentEnum getEnumByDisplay(String display) {
        switch (display) {
            case "Tiền mặt":
                return ModeOfPaymentEnum.CASH;
            case "Quẹt thẻ":
                return ModeOfPaymentEnum.CARD_SWIPE;
            case "Chuyển khoản":
                return ModeOfPaymentEnum.BANK_TRANSFER;
            default:
                return null;
        }
    }
}
