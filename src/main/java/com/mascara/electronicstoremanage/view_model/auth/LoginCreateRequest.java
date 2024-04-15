package com.mascara.electronicstoremanage.view_model.auth;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:03 CH
 * Filename  : LoginCreateRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginCreateRequest {
    private String userName;
    private String password;
}

