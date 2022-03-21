package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.nuonuo.constant.Constants.*;

@Getter
@Setter
@ToString
public class UserRegisterDTO {

  private String uname;
  @NotBlank(message = "{user.register.not-blank.password}")
  @Pattern(regexp = REGEX_USER_LOGIN_PASSWORD, message = "{user.register.patten.password}")
  private String password;
  @NotBlank(message = "{user.register.not-blank.password}")
  @Pattern(regexp = REGEX_USER_LOGIN_PASSWORD, message = "{user.register.patten.password}")
  private String rePassword;

//  private boolean isSuspend=false;

}
