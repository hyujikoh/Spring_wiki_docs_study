package com.example.wiki_docs_study.gym.kakao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private double lat;


    @Column
    private double lng;
/**
 * 이름
 * 도로명 주소
 * 전화번호
 * 위도
 * 경도
 * */





}
