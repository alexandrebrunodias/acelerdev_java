package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;

    @Email
    @NotNull
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @CreatedDate
    private LocalDate createdAt;

    @OneToMany
    private List<Submission> submissions;

    @OneToMany
    private List<Candidate> candidates;

}
