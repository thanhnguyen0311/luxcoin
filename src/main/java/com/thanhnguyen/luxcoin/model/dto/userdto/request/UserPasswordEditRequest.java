package com.thanhnguyen.luxcoin.model.dto.userdto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPasswordEditRequest {
    private String password;
    private String newPassword;
}
