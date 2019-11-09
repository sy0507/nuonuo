package com.example.nuonuo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class PostActivityDTO {

  public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

  @NotBlank(message = "{activity.not-blank.title}")
  @Size(min = 3, max = 20, message = "{activity.size.title}")
  private String title;

  @NotBlank(message = "{activity.not-blank.address}")
  @Size(min = 5, max = 40, message = "{activity.size.address}")
  private String address;

  @NotBlank(message = "{activity.not-blank.content}")
  @Size(min = 10, max = 200, message = "{activity.size.content}")
  private String content;

  @Positive(message = "{activity.positive.cover-pic-id}")
  @JsonProperty("coverImageId")
  private Integer coverPicId;

  /**
   * 其他图片id
   */
  private List<Integer> imageIds;
}
