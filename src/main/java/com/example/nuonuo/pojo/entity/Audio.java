package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "audio_info")
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audio_id")
    private Integer audioId;
    /**
     * 音频名字
     */
    @Column(name = "audio_name")
    private String audioName;

    @Column(name = "audio_type_id")
    private Integer audioTypeId;

    @Column(name = "good_number")
    private Integer goodNumber;

    private String price;

    private String description;
    @Column(name = "cover_id")
    private Integer coverId;

    @Column(name = "content_id")
    private Integer contentId;

    private Integer uid;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "collect_number")
    private Integer collectNumber;


    @Column(name = "watch_number")
    private Integer watchNumber;

    private String duration;

    @Column(name = "comment_number")
    private Integer commentNumber;

    private Integer status;


}
