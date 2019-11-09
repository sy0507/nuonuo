package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class PhotoDTO {
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
