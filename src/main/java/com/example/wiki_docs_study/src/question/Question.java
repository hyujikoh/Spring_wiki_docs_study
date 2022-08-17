package com.example.wiki_docs_study.src.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.wiki_docs_study.src.answer.Answer;
import com.example.wiki_docs_study.src.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {


    @Id // 해당 id 속성을 기본키로 지정한다.
    // id 값을 null 하면 DB 가 auto increment 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;


    @ManyToMany
    Set<SiteUser> voter;
}