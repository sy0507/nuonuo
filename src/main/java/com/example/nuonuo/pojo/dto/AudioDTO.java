package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@ToString
public class AudioDTO {
    /**
     * 音频名字
     */
    @Column(name = "audio_name")
    private String audioName;

    @Column(name = "audio_type_id")
    private Integer audioTypeId;

    private String price;

    private String description;
    @Column(name = "cover_id")
    private Integer coverId;

    @Column(name = "content_id")
    private Integer contentId;


    private String duration;
}
