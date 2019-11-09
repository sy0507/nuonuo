package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter

@Setter
@ToString
public class PhotoVO {
  /**
   * 名字
   */
  private String name;

  /**
   * qq
   */
  private String qq;


  /**
   * Wechat
   */
  private  String weChat;
  /**
   * 图片ID
   */
  @Column(name = "photo_pic_id")
  private  Integer photoPicId;

  private  String phone;




}
