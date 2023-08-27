package com.thanhnguyen.luxcoin.model.dto.cryptodto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CryptoListResponse {
    private String name;
    private String title;
    private byte[] image;
    private Boolean isActive;
}
