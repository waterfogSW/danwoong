package com.dku.danwoong.user.model;

import com.dku.danwoong.common.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String senderId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    protected User() {/*no-op*/}

    public User(String senderId, Provider provider) {
        this.senderId = senderId;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }
}
