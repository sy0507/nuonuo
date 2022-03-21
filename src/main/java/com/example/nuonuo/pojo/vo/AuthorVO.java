package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
@Getter
@Setter
@ToString
public class AuthorVO {
    private Integer uid;

    private String name;

    @Column(name = "head_pic_id")
    private Integer headPicId;

    /**
     * 头像地址
     */
    private String headPicUrl;

    private String introduction;


    private boolean isFollow=false;
}
