package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "pay")
public class Pay {
    @Id
    private Integer id;

    private Integer uid;

    @Column(name = "audio_id")
    private Integer audioId;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "out_trade_no")
    private String outTradeNo;

}
