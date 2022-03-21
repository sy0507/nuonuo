package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class LAudioVO {
    @Column(name = "audio_id")
    private Integer audioId;
    /**
     * 音频名字
     */
    @Column(name = "audio_type_id")
    private Integer audioTypeId;
    @Column(name = "audio_name")
    private String audioName;
    @Column(name = "good_number")
    private Integer goodNumber;
    private String description;
    @Column(name = "cover_id")
    private Integer coverId;

    private Integer uid;

    private String audioType;

    private String coverUrl;
}
