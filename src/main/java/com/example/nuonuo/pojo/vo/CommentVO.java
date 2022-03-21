package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class CommentVO {
    @Id
    private Integer id;

    private Integer uid;
    @Column(name = "audio_id")
    private Integer audioId;

    private String content;

    private Long time;

    private String name;

    private String headPicUrl;

    @Column(name = "point_on_num")
    private Integer pointOnNum;

    @Column(name = "point_out_num")
    private Integer pointOutNum;
}
