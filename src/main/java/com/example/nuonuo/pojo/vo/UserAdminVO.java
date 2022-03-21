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
public class UserAdminVO {

    @Column(name = "uid")
    private Integer uid;

    /**
     * 用户名
     */
    private String uname;


    private String name;

    private Integer fans;

    private String phone;

    private String mail;


    @Column(name = "is_suspend")
    private boolean isSuspend;


    @Column(name = "audio_num")
    private Integer audioNum;

    @Column(name = "suspend_start_time")
    private Long suspendStartTime;

    @Column(name = "suspend_end_time")
    private Long suspendEndTime;

    @Column(name = "suspend_reason")
    private String suspendReason;




}
