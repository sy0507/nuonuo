package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
public class Message {
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
   * 多上传图片
   */
  private List<Integer> imageIds;
}
