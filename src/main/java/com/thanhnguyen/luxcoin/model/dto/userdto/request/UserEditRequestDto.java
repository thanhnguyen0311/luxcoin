package com.thanhnguyen.luxcoin.model.dto.userdto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEditRequestDto {
    private String name;
    private String email;
    private String phone;
    private String description;
    private byte[] image;
}
