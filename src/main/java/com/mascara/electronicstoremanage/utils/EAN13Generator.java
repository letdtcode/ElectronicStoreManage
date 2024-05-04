package com.mascara.electronicstoremanage.utils;

import java.util.Random;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 28/04/2024
 * Time      : 6:26 CH
 * Filename  : EAN13Generator
 */
public class EAN13Generator {
    private static EAN13Generator instance = null;

    public static EAN13Generator getInstance() {
        if (instance == null)
            instance = new EAN13Generator();
        return instance;
    }

    private EAN13Generator() {

    }

    public String generateRandomEAN13() {
        String random12Digits = generateRandom12Digits();
        int checksumDigit = calculateChecksumDigit(random12Digits);
        return random12Digits + checksumDigit;
    }

    private String generateRandom12Digits() {
        Random random = new Random();
        String random12Digits = "";
        for (int i = 0; i < 12; i++) {
            random12Digits += random.nextInt(10);
        }
        return random12Digits;
    }

    private int calculateChecksumDigit(String mMembershipId) {
        int total_sum = 0;
        for (int i = 0; i < mMembershipId.length(); i++) {
            if (i % 2 != 0) {
                total_sum = total_sum + Integer.parseInt("" + mMembershipId.charAt(i)) * 3;
            } else {
                total_sum = total_sum + Integer.parseInt("" + mMembershipId.charAt(i));
            }
        }
        return (10 - total_sum % 10) % 10;
    }
}
