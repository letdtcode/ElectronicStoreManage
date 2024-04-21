package com.mascara.electronicstoremanage.view_model.customer;

import com.mascara.electronicstoremanage.enums.customer.CustomerStatusEnum;
import com.mascara.electronicstoremanage.enums.general.SexEnum;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:17 CH
 * Filename  : CustomerViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerViewModel {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private SexEnum sex;
    private CustomerStatusEnum status;
}
