package com.example.nuonuo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.nuonuo.constant.Constants.REGEX_PHONE_NUMBER;

@Getter
@Setter
@ToString
  public class UserLoginDTO {
//  @NotBlank(message = "{user.login.not-blank.phone}")
  @Pattern(regexp = REGEX_PHONE_NUMBER, message = "{user.login.pattern.phone}")
  @JsonProperty("account")
  private String phone;
  @NotBlank(message = "{user.login.not-blank.password}")
  @Size(min = 6, max = 20, message = "{user.login.size.password}")
  private String password;
}
