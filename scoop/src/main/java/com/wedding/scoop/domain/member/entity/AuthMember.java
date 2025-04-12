package com.wedding.scoop.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthMember {
    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Auth auth;

    public AuthMember(Member member, Auth auth) {
        this.member = member;
        this.auth = auth;
    }
}
