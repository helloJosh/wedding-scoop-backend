package com.wedding.scoop.domain.member.entity;

import com.wedding.scoop.domain.member.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Setter
    private String nickname;

    @Column(length = 100)
    @Setter
    private String name;

    private String email;
    private String uuid;

    @Setter
    private String ageRange;

    private Role role;
    private LocalDateTime expectedWeddingDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Setter
    private LocalDateTime latestLoginAt;
    private Boolean active = Boolean.TRUE;

    @ManyToOne(cascade = CascadeType.ALL)
    private Oauth oauth;

    public Member(String uuid, Oauth oauth) {
        this.uuid = uuid;
        this.oauth = oauth;
    }
}
