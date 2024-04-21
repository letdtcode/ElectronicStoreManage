package com.mascara.electronicstoremanage.view_model.sale;

import lombok.*;

import java.time.LocalDate;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 20/04/2024
 * Time      : 6:03 CH
 * Filename  : OrderWaitingViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderWaitingViewModel {
    private Long id;
    private LocalDate createdDate;
    private String nameStaff;
    private String nameCustomer;
}
