package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "activity_pic")
@Getter
@Setter
public class ActivityPic {
  @Id
  @Column(name = "activity_id")
  private Integer activityId;

  @Id
  @Column(name = "pic_id")
  private Integer picId;
}
