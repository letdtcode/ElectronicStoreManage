package com.mascara.electronicstoremanage.services.auth;

import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.repositories.staff.StaffRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.auth.LoginCreateRequest;

import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 10/04/2024
 * Time      : 12:40 SA
 * Filename  : AuthServiceImpl
 */

public class AuthServiceImpl implements AuthService {
    private static AuthServiceImpl instance = null;

    public static AuthServiceImpl getInstance() {
        if (instance == null)
            instance = new AuthServiceImpl();
        return instance;
    }

    private AuthServiceImpl() {

    }

    @Override
    public boolean login(LoginCreateRequest request) {
        Optional<Staff> staff = StaffRepositoryImpl.getInstance().findByUserName(request.getUserName());
        if (staff.isPresent() && staff.get().getPassword().equals(request.getPassword()))
            return true;
        return false;
    }
}
