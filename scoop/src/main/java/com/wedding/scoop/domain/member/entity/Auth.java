package com.wedding.scoop.domain.member.entity;

import com.wedding.scoop.domain.member.entity.enums.AuthType;
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

    private AuthType type;

    public Auth(AuthType type) {
        this.type = type;
    }
}
