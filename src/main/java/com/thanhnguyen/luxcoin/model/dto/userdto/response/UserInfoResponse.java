package com.thanhnguyen.luxcoin.model.dto.userdto.response;

import com.thanhnguyen.luxcoin.model.entity.Role;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String name;
    private String role;
    private byte[] image;
}
