package com.mascara.electronicstoremanage.repositories.staff;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffUpdateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : StaffRepository
 */
public interface StaffRepository extends ModifyEntityRequest<StaffCreateRequest, StaffUpdateRequest, Long>,
        RetrieveEntityRequest<StaffViewModel, StaffPagingRequest, Long> {
}
