package com.thanhnguyen.luxcoin.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="username",nullable = false,unique=true)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="email",unique=true)
    private String email;

    @Column(name="phone",unique=true)
    private String phone;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name="date")
    private LocalDateTime date ;

    @Column(name="lastseen")
    private LocalDateTime lastSeen ;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="image")
    private byte[] image;

    @Column(name="description",length = 65535,columnDefinition="Text")
    private String description;
}
