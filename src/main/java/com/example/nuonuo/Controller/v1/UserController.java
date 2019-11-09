package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.dto.PutUserProfileDTO;
import com.example.nuonuo.pojo.dto.UserLoginDTO;
import com.example.nuonuo.pojo.dto.UserRegisterDTO;
import com.example.nuonuo.pojo.dto.UserResetPasswordDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.UserService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.nuonuo.constant.Constants.REGEX_PHONE_NUMBER;
import static com.example.nuonuo.constant.Constants.REGEX_USER_LOGIN_PASSWORD;


@RestController
@RequestMapping("/v1/users")
@Slf4j
@Validated
public class UserController  {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }



  /**
   * 用户注册接口
   * @param dto
   * @return
   */
  @PostMapping("/register")
   public WebAsyncTask<Object> register(@RequestBody @Validated UserRegisterDTO dto){
    return new WebAsyncTask<>(() -> {
      userService.register(dto);
      return JsonResult.ok();
    });
  }


  /**
   * 用户登录
   * @param dto
   * @return
   */
  @PostMapping("/login")
  public WebAsyncTask<Object> login(@RequestBody @Validated UserLoginDTO dto){
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.login(dto)));
  }

  /**
   * 修改密码
   * @param oldPassword
   * @param newPassword
   * @param user
   * @return
   */

  @PutMapping("/password")
  public WebAsyncTask<Object> modifyPassword(
                                             @Size(min = 6, max = 20, message = "{user.modify-password.size.old-password}")
                                               String oldPassword,

                                             @Pattern(regexp = REGEX_USER_LOGIN_PASSWORD, message = "{user.modify-password.pattern.new-password}")
                                               String newPassword,
                                             @Token User user){
    return new WebAsyncTask<>(() -> {
      userService.modifyPassword(oldPassword, newPassword, user);
      return JsonResult.ok();
    });
  }



  /**
   * 忘记密码
   * @param dto
   * @return
   */

  @PostMapping("/password/reset")
  public WebAsyncTask<Object> resetPassword(@Validated @RequestBody UserResetPasswordDTO dto) {
    return new WebAsyncTask<>(() -> {
      userService.resetPassword(dto);
      return JsonResult.ok();
    });
  }

  /**
   * 修改个人信息
   * @param userProfileDTO
   * @param user
   * @return
   */
  @PutMapping("/profile")
  public WebAsyncTask<Object> updateUserProfile(@RequestBody @Validated PutUserProfileDTO userProfileDTO,
                                                @Token User user) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.updateProfile(user.getUid(), userProfileDTO)));
  }

  /**
   * 查看自己信息
   * @param user
   * @return
   */
  @GetMapping("/info")
  public WebAsyncTask<Object> getUserInfo(@Token User user) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.getUserInfoVO(user)));
  }


  /**
   * 收藏活动接口
   *
   * @param activityId
   * @param user
   * @return
   */
  @PostMapping("/activities/{id}/collect")
  public WebAsyncTask<Object> collectActivity(@PathVariable("id") Integer activityId, @Token User user) {
    return new WebAsyncTask<>(() -> {
      userService.collectActivity(activityId, user);
      return JsonResult.ok();
    });



  }

  /**
   * 获取用户已收藏活动
   * @param user
   * @return
   */
  @GetMapping("/list")
  public WebAsyncTask<Object> listCollectAlumni(@Token User user) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.acGetList(user)));
  }






}
