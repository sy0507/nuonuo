package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class PicDTO {
//    private  Integer id;
//图片名称
    @Column(name = "name")
    private String name;
    @Column(name = "picture_base",columnDefinition = "TEXT")
    private  String picBase;
}
