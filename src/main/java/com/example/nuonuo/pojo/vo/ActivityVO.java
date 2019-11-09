package com.example.nuonuo.pojo.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
public class ActivityVO {

  @JsonProperty("activityId")
  private Integer id;
  private String coverImgUrl;
  private String title;
  @Column(name = "originator_id")
  private Integer originatorId;

  private String content;

  private List<String> otherPicUrls;
  private List<Integer> otherPicIds;
}
