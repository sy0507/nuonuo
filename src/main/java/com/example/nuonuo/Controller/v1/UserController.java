package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.dto.*;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.UserService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.example.nuonuo.constant.Constants.REGEX_PHONE_NUMBER;
import static com.example.nuonuo.constant.Constants.REGEX_USER_LOGIN_PASSWORD;


@RestController
@RequestMapping("/users")
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

//  @PutMapping("/password")
//  public WebAsyncTask<Object> modifyPassword(
//                                             @Size(min = 6, max = 20, message = "{user.modify-password.size.old-password}")
//                                               String oldPassword,
//
//                                             @Pattern(regexp = REGEX_USER_LOGIN_PASSWORD, message = "{user.modify-password.pattern.new-password}")
//                                               String newPassword,
//                                             @Token User user){
//    return new WebAsyncTask<>(() -> {
//      userService.modifyPassword(oldPassword, newPassword, user);
//      return JsonResult.ok();
//    });
//  }



  /**
   * 忘记密码
   * @param dto
   * @return
   */

//  @PostMapping("/password/reset")
//  public WebAsyncTask<Object> resetPassword(@Validated @RequestBody UserResetPasswordDTO dto) {
//    return new WebAsyncTask<>(() -> {
//      userService.resetPassword(dto);
//      return JsonResult.ok();
//    });
//  }

  /**
   * 修改个人信息
   * @param userProfileDTO
   * @param user
   * @return
   */
  @PutMapping("/profile")
  public WebAsyncTask<Object> updateUserProfile(@RequestBody @Validated PutUserProfileDTO userProfileDTO,
                                                @RequestParam(value = "uid") Integer uid) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.updateProfile(uid, userProfileDTO)));
  }

  /**
   * 查看信息
   * @return
   */
  @GetMapping("/info/{id}")
  public WebAsyncTask<Object> getUserInfo( @PathVariable("id") Integer id) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.getUserInfoVO(id)));
  }

  /**
   * 用户收藏音频接口
   */
  @PostMapping("/collect/{id}")
  public WebAsyncTask<Object> collectAudio(@PathVariable("id") Integer audioId,@RequestParam(value = "uid") Integer uid) {
    return new WebAsyncTask<>(() -> {
      userService.collectAudio(audioId, uid);
      return JsonResult.ok();
    });
  }

  /**
   * 用户点赞接口
   * @param audioId
   * @param user
   * @return
   */

  @PostMapping("/good/{id}")
  public WebAsyncTask<Object> goodAudio(@PathVariable("id") Integer audioId, @RequestParam(value = "uid") Integer uid) {
    return new WebAsyncTask<>(() -> {
      userService.goodAudio(audioId, uid);
      return JsonResult.ok();
    });
  }

  /**
   * 观看视频
   * @param audioId
   * @return
   */

  @PostMapping("/watch/{id}")
  public WebAsyncTask<Object> watchAudio(@PathVariable("id") Integer audioId) {
    return new WebAsyncTask<>(() -> {
      userService.watchAudio(audioId);
      return JsonResult.ok();
    });
  }

  /**
   * 关注接口
   * @param id
   * @param user
   * @return
   */
  @PostMapping("/follow/{id}")
  public WebAsyncTask<Object> follow(@PathVariable("id") Integer id, @RequestParam(value = "uid") Integer uid) {
    return new WebAsyncTask<>(() -> {
      userService.follow(id, uid);
      return JsonResult.ok();
    });
  }

  /**
   * 获取用户所有收藏的接口
   * @param id
   * @return
   */

  @GetMapping("/collect_list/{id}")
  public WebAsyncTask<Object> getCollectList(@PathVariable("id") Integer id){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getCollectList(id)));
  }

  /**
   * 获取该用户的关注用户信息
   * @param id
   * @return
   */

  @GetMapping("/follow_list/{id}")
  public WebAsyncTask<Object> getFollowList(@PathVariable("id") Integer id){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getFollowList(id)));
  }


  @GetMapping("/fans_list/{id}")
  public WebAsyncTask<Object> getFanList(@PathVariable("id") Integer id){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getFanList(id)));
  }


  @GetMapping("/upload_list/{id}")
  public WebAsyncTask<Object> getUploadList(@PathVariable("id") Integer id){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getUploadList(id)));
  }

  @GetMapping("/upload/{audio_id}")
  public WebAsyncTask<Object> getUpload(@PathVariable("audio_id") Integer audioId){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getUpload(audioId)));
  }

  @PutMapping("/update/{audio_id}")
  public WebAsyncTask<Object> updateAudio(@PathVariable("audio_id") Integer audioId,@RequestBody @Validated AudioDTO audioDTO) {
    return new WebAsyncTask<>(() -> JsonResult.ok(userService.updateAudio(audioId, audioDTO)));
  }

//  @Scheduled(fixedDelay = 5000)
  @GetMapping("/loginNumber")
  public WebAsyncTask<Object> getLoginNumber(){
    return new WebAsyncTask<>(()-> JsonResult.ok(userService.getLoginNumber()));
  }

}
