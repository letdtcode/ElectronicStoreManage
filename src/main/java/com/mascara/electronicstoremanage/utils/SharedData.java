package com.mascara.electronicstoremanage.utils;

import com.mascara.electronicstoremanage.entities.Staff;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 21/04/2024
 * Time      : 8:12 CH
 * Filename  : SharedCustomer
 */
public class SharedData {
    private Long customerId = 1L;
    private String nameCustomer = "Khách bán lẻ";
    private Staff staffCurrentLogin = null;

    private static SharedData instance = null;

    public static SharedData getInstance() {
        if (instance == null)
            instance = new SharedData();
        return instance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Staff getStaffCurrentLogin() {
        return staffCurrentLogin;
    }

    public void setStaffCurrentLogin(Staff staffCurrentLogin) {
        this.staffCurrentLogin = staffCurrentLogin;
    }
}
