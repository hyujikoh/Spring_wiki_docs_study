package com.example.wiki_docs_study.answer;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.example.wiki_docs_study.question.Question;
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

    @ManyToOne
    private Question question;
}