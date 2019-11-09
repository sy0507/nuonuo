package com.example.nuonuo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class PutUserProfileDTO {
  @Positive(message = "{user.profile.positive.head-pic-id}")
  private Integer headPicId;
  @Pattern(regexp = "(保密|男|女)", message = "{user.profile.pattern.gender}")
  @JsonProperty("gender")
  private String sexual;
  @Size(max = 15, message = "{user.profile.size.qq}")
  private String qq;

  private String name;

  private String weixin;

}
