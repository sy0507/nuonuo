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
public class AudioAdminVO {
    @Column(name = "audio_id")
    private Integer audioId;
    /**
     * 音频名字
     */
    @Column(name = "audio_name")
    private String audioName;

    @Column(name = "audio_type_id")
    private Integer audioTypeId;

    private Integer price;

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

    private String duration;

    private Integer status;

    private String reason;
    @Column(name = "audit_time")
    private Long auditTime;
}
