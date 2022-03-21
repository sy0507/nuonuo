package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class PayVO {
    @Id
    private Integer id;

    private Integer uid;

    @Column(name = "audio_id")
    private Integer audioId;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "out_trade_no")
    private String outTradeNo;

    @Column(name = "audio_name")
    private String audioName;
}
