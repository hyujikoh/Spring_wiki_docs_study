package com.example.wiki_docs_study.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.example.wiki_docs_study.question.Question;
import com.example.wiki_docs_study.user.SiteUser;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;


    @ManyToMany
    Set<SiteUser> voter;
}