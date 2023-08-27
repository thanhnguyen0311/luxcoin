package com.thanhnguyen.luxcoin.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Builder
@Table(name = "cryptos")
public class Crypto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="title")
    private String title;

    @Column(name="image")
    private byte[] image;

    @Column(name="is_active")
    private Boolean isActive;
}
