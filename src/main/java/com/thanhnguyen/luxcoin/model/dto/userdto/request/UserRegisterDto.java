package com.thanhnguyen.luxcoin.model.dto.userdto.request;


import com.thanhnguyen.luxcoin.model.entity.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UserRegisterDto{
    @NotEmpty(message = "Vui lòng nhập họ tên")
    @Size(max = 255, message = "Độ dài không quá 255 kí tự")
    private String name;
    @NotEmpty(message = "Vui lòng nhập tên đăng nhập")
    @Size(min = 8,max = 255, message = "Độ dài không quá 255 kí tự")
    private String username ;
    @NotEmpty(message = "Vui lòng nhập mật khẩu")
    @Size(min = 8,max = 255, message = "Độ dài không quá 255 kí tự")
    private String password;
    @NotEmpty(message = "Vui lòng nhập email")
    @Size(max = 255, message = "Độ dài không quá 255 kí tự")
    @Email
    private String email;

    private LocalDateTime date;

    private LocalDateTime lastSeen;

    private Boolean isActive;

    private Boolean isDeleted;

    private Role role = new Role();

    public void update(){
        this.date = LocalDateTime.now();
        this.lastSeen  = LocalDateTime.now();
        this.isActive = true;
        this.isDeleted = false;
        this.role.setRoleId(2);
    }
}
