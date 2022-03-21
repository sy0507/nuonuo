package com.example.nuonuo.pojo.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@ToString
public class FUserVO {
    private Integer uid;

    private String name;

    @Column(name = "head_pic_id")
    private Integer headPicId;

    /**
     * 头像地址
     */
    private String headPicUrl;
}
