package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserInfoVO {
  private Integer uid;

  /**
   * 密码
   */
  private String password;
  /**
   * token
   */
  private String accessToken;
  /**
   * 昵称
   */
  private String name;
  /**
   * 头像id
   */
  @Column(name = "head_pic_id")
  private Integer headPicId;

  /**
   * 头像地址
   */
  private String headPicUrl;
  /**
   * 账户余额
   */
  @Column(name = "account_balance")
  private String accountBalance;
  /**
   * 关注的用户数
   */
  private Integer follows;
  /**
   * 粉丝数
   */
  private Integer fans;
  /**
   * 收藏的视频数
   */
  private Integer favor;

  private String introduction;


  @Column(name = "fish_num")
  private Integer fishNum;

  private Integer level;
  @Column(name = "register_time")
  private Long registerTime;

  @Column(name = "second_avatar_id")
  private Integer secondAvatarId;

  private String secondAvatarUrl;

  @Column(name = "personal_avatar_id")
  private Integer personalAvatarId;

  private String personalAvatarUrl;

  @Column(name = "last_login_time")
  private Long lastLoginTime;

  @Column(name = "is_suspend")
  private boolean isSuspend;

  @Column(name = "audio_num")
  private Integer audioNum;
}
