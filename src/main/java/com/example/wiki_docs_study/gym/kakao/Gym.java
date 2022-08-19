package com.example.wiki_docs_study.gym.kakao;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String gymName;


    @Column
    private String gymAddress;

    @Column
    private String gymPhoneNumber;


    @Column
    private int per_1_month;
    @Column
    private int per_3_months;
    @Column
    private int per_6_months;
    @Column
    private int per_12_months;
    @Column
    private int pt_price;


    @Column
    private double lat;


    @Column
    private double lng;


    @ColumnDefault("0")
    private Boolean deleteYn;

    @CreatedDate
    @Column(nullable = true, updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true, updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;
/**
 * 이름
 * 도로명 주소
 * 전화번호
 * 위도
 * 경도
 * */





}
