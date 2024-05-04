package com.mascara.electronicstoremanage.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 22/04/2024
 * Time      : 3:32 CH
 * Filename  : PasswordHashingUtils
 */
public class PasswordHashingUtils {
    private static PasswordHashingUtils instance = null;

    public static PasswordHashingUtils getInstance() {
        if (instance == null)
            instance = new PasswordHashingUtils();
        return instance;
    }

    private PasswordHashingUtils() {

    }

    public String getHash(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean verify(String password, String bcryptHashString) {
        return BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString).verified;
    }
}
