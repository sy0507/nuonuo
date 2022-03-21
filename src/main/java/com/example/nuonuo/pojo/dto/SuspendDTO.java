package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class SuspendDTO {
    private Integer uid;

    @Column(name = "suspend_start_time")
    private Long suspendStartTime;

    @Column(name = "suspend_end_time")
    private Long suspendEndTime;

    @Column(name = "suspend_reason")
    private String suspendReason;

    @Column(name = "admin_id")
    private Integer adminId;

}
