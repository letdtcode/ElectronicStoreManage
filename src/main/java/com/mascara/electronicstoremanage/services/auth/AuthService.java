package com.mascara.electronicstoremanage.services.auth;

import com.mascara.electronicstoremanage.view_model.auth.LoginCreateRequest;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 10/04/2024
 * Time      : 12:40 SA
 * Filename  : AuthService
 */
public interface AuthService {
    boolean login(LoginCreateRequest request);
}
