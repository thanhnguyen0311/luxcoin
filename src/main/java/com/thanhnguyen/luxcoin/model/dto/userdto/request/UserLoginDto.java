package com.thanhnguyen.luxcoin.model.dto.userdto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginDto {

    @NotEmpty(message = "Nhập tên đăng nhập hoặc email....")
    private String username ;

    @NotEmpty(message = "Vui lòng nhập mật khẩu!")
    private String password;
}
