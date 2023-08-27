package com.thanhnguyen.luxcoin.model.dto.userdto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AuthenticatedResponse {
    private String token;
    private String role;
}
