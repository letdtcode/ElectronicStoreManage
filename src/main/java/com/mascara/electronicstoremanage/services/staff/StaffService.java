package com.mascara.electronicstoremanage.services.staff;

import com.mascara.electronicstoremanage.entities.Staff;
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
 * Filename  : StaffService
 */
public interface StaffService {
    Long insertStaff(StaffCreateRequest request);

    boolean updateStaff(StaffUpdateRequest request);

    boolean deleteStaff(Long id);

    StaffViewModel retrieveStaffById(Long id);

    List<StaffViewModel> retrieveAllStaff(StaffPagingRequest request);

    Optional<Staff> getInfoByEmail(String email);
    boolean updatePassword(Long idStaff, String newPassword);
}
