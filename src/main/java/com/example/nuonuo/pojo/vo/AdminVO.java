package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class AdminVO {

    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "admin_name")
    private String adminName;

    private String password;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "avatar_id")
    private Integer avatarId;

    private String name;

    private String avatarUrl;

}
