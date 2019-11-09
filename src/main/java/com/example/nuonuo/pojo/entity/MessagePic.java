package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "message_pic")
@Getter
@Setter
public class MessagePic {
  @Id
  @Column(name = "message_id")
  private Integer messageId;

  @Id
  @Column(name = "pic_id")
  private Integer picId;
}
