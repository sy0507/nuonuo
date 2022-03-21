package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class PicVO {

    //    图片的id
    @Id
    private  Integer id;
    //图片名称
    @Column(name = "name")
    private String name;
    @Column(name = "picture_base")
    private  String picBase;

}
