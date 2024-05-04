package com.mascara.electronicstoremanage.services.staff;

import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.repositories.staff.StaffRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffUpdateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;

import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:36 CH
 * Filename  : StaffServiceImpl
 */
public class StaffServiceImpl implements StaffService {

    private static StaffServiceImpl instance = null;

    public static StaffServiceImpl getInstance() {
        if (instance == null)
            instance = new StaffServiceImpl();
        return instance;
    }

    private StaffServiceImpl() {

    }

    @Override
    public Long insertStaff(StaffCreateRequest request) {
        return StaffRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateStaff(StaffUpdateRequest request) {
        return StaffRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteStaff(Long id) {
        return StaffRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public StaffViewModel retrieveStaffById(Long id) {
        return StaffRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<StaffViewModel> retrieveAllStaff(StaffPagingRequest request) {
        return StaffRepositoryImpl.getInstance().retrieveAll(request);
    }

    @Override
    public Optional<Staff> getInfoByEmail(String email) {
        return StaffRepositoryImpl.getInstance().getInfoByEmail(email);
    }

    @Override
    public boolean updatePassword(Long idStaff, String newPassword) {
        return StaffRepositoryImpl.getInstance().updatePassword(idStaff, newPassword);
    }
}
