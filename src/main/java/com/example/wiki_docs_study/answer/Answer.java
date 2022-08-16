package com.example.wiki_docs_study.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import com.example.wiki_docs_study.question.Question;
import com.example.wiki_docs_study.user.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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