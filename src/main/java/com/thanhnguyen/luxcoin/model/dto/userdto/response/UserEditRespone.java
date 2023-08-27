package com.thanhnguyen.luxcoin.model.dto.userdto.response;

import com.thanhnguyen.luxcoin.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEditRespone {

    private String name;
    private String username;
    private String email;
    private String phone;
    private String description;
    private Role role;
    private LocalDateTime date ;
    private LocalDateTime lastSeen ;
    private Boolean isActive;
    private byte[] image;

}
