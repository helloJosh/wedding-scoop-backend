package com.wedding.scoop.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String type;

    public Auth(String type) {
        this.type = type;
    }
}
