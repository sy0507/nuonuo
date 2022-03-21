package com.example.nuonuo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class PutUserProfileDTO {
  @Positive(message = "{user.profile.positive.head-pic-id}")
  private Integer headPicId;

  private String introduction;

  private String name;

  private String phone;

  private String mail;

  @Column(name = "second_avatar_id")
  private Integer secondAvatarId;

  @Column(name = "personal_avatar_id")
  private Integer personalAvatarId;

}
