package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "carousel_info")
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audio_id")
    private Integer audioId;
    /**
     * 音频名字
     */
    @Column(name = "audio_name")
    private String audioName;

    @Column(name = "cover_id")
    private Integer coverId;



}
