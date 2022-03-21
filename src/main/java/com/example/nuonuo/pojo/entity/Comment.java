package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer uid;
    @Column(name = "audio_id")
    private Integer audioId;

    private String content;

    private Long time;

    @Column(name = "point_on_num")
    private Integer pointOnNum;

    @Column(name = "point_out_num")
    private Integer pointOutNum;


}
