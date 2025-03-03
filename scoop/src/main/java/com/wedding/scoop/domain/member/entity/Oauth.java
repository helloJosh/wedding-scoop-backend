package com.wedding.scoop.domain.member.entity;

import com.wedding.scoop.domain.member.entity.enums.Provider;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Oauth {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private Provider provider;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Oauth(Provider provider) {
        this.provider = provider;
    }
}
