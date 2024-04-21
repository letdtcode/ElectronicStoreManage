package com.mascara.electronicstoremanage.controllers.staff.sale;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 21/04/2024
 * Time      : 8:12 CH
 * Filename  : SharedCustomer
 */
public class SharedCustomer {
    private Long customerId = 1L;
    private String nameCustomer = "Khách bán lẻ";

    private static SharedCustomer instance = null;

    public static SharedCustomer getInstance() {
        if (instance == null)
            instance = new SharedCustomer();
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

    public static void setInstance(SharedCustomer instance) {
        SharedCustomer.instance = instance;
    }
}
