package com.mascara.electronicstoremanage.enums.staff;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 11:10 CH
 * Filename  : StaffStatusEnum
 */
public enum StaffStatusEnum {
    ACTIVE("ACTIVE", "Đang làm việc"),
    INACTIVE("INACTIVE", "Đã nghỉ việc");

    private final String key;
    private final String display;

    StaffStatusEnum(String key, String display) {
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
