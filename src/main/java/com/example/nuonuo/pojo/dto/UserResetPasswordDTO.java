package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.nuonuo.constant.Constants.*;


@Getter
@Setter
@ToString
public class UserResetPasswordDTO {
  @NotBlank(message = "{user.register.not-blank.email}")
  private String email;

  @NotBlank(message = "{user.modifyPassword.not-blank.password}")
  @Pattern(regexp = REGEX_USER_LOGIN_PASSWORD, message = "{user.register.patten.password}")
  private String password;

  @NotBlank(message = "{user.code}")
  private String code;
}
