package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
public class Activity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String content;


  private String title;

  @Column(name = "cover_pic_id")
  private Integer coverPicId;

  @Column(name = "originator_id")
  private Integer originatorId;

  private List<Integer> otherPicIds;
}
