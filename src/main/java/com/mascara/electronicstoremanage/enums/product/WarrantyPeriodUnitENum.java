package com.mascara.electronicstoremanage.enums.product;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 12/04/2024
 * Time      : 6:12 CH
 * Filename  : WarrantyPeriodUnitENum
 */
public enum WarrantyPeriodUnitENum {
    BY_MONTH("BY_MONTH", "Theo tháng"),
    BY_YEAR("BY_YEAR", "Theo năm");

    private String key;
    private String display;

    WarrantyPeriodUnitENum(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return key;
    }

    public String getDisplay() {
        return display;
    }

    public static WarrantyPeriodUnitENum getEnumByDisplay(String display) {
        switch (display) {
            case "Theo tháng":
                return WarrantyPeriodUnitENum.BY_MONTH;
            case "Theo năm":
                return WarrantyPeriodUnitENum.BY_YEAR;
            default:
                return null;
        }
    }
}
