package com.mascara.electronicstoremanage.view_model.staff;

import com.mascara.electronicstoremanage.enums.general.SexEnum;
import com.mascara.electronicstoremanage.enums.staff.StaffStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 7:51 CH
 * Filename  : StaffCreateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffCreateRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private SexEnum sex;
    private String userName;
    private String password;
    private StaffStatusEnum status;
    private String roleName;
}
