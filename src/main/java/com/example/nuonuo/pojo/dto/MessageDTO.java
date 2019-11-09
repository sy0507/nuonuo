package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
public class MessageDTO {

  /**
   * 发送消息用户id
   */
  private String uid;

  /**
   * 发送内容
   */
  private String content;


  /**
   * 接受用户id
   */
  @Column(name = "send_id")
  private String sendId;

  /**
   * 用户上传图片数组
   */
  private List<Integer>  imageIds;
}
