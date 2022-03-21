package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class CardVO {
    @Column(name = "audio_id")
    private Integer audioId;
    /**
     * 音频名字
     */
    @Column(name = "audio_name")
    private String audioName;

    @Column(name = "cover_id")
    private Integer coverId;

    private String coverUrl;

    @Column(name = "good_number")
    private Integer goodNumber;

    private String price;

}
