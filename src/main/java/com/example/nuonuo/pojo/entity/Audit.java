package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "audit_info")
public class Audit {
    @Id
    private Integer id;
    @Column(name = "audio_id")
    private Integer audioId;

    private String reason;

    @Column(name = "audit_time")
    private Long auditTime;

    @Column(name = "admin_id")
    private Integer adminId;
}
