package com.thanhnguyen.luxcoin.model.dto.cryptodto.request;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CryptoAddDto {
    private String name;

    private String title;

    private byte[] image;

    private Boolean isActive = true;

    public CryptoAddDto(String name, String title, byte[] image) {
        this.name = name;
        this.title = title;
        this.image = image;
    }
}
