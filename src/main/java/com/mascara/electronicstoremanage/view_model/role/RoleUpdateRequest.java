package com.mascara.electronicstoremanage.view_model.role;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:26 CH
 * Filename  : RoleUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleUpdateRequest {
    private Long id;
    private String roleName;
}
