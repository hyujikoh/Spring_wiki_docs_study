package com.example.wiki_docs_study.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String user_password;

    @Column(unique = true)
    private String email;


}