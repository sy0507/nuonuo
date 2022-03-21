package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class AudioVO {
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

    private String audioType;

    private String coverUrl;

    private String contentUrl;

    @Column(name = "collect_number")
    private Integer collectNumber;


    @Column(name = "watch_number")
    private Integer watchNumber;

    private String duration;

    private Integer commentNumber;

//    private String com="aaa";

    private boolean isFollow=false;

    private boolean isGood=false;

    private boolean isCollect=false;

    private boolean isBelong=false;
}
