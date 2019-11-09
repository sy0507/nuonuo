package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class Photo {
  /**
   * 主键
   */
  @Id
  private Integer id;


  /**
   * 拍照照片
   */
  @Column(name = "photo_pic_id")
  private Integer photoPicId;
  /**
   * 用户id
   */
  private Integer uid;
  /**
   * 牌照号
   */
  @Column(name = "photo_id")
  private String photoId;
}
