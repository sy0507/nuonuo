package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
public class User {
  @Id
  @Column(name = "uid")
  private Integer uid;

  /**
   * 用户名
   */
  private String uname;
  /**
   * 密码
   */
  private String password;
  /**
   * token
   */
  @Column(name = "access_token")
  private String accessToken;
  /**
   * 电话
   */
  private String phone;
  /**
   * 昵称
   */
  private String name;

  @Column(name = "head_pic_id")
  private Integer headPicId;

  private String mail;

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
  @Column(name = "personal_avatar_id")
  private Integer personalAvatarId;

  @Column(name = "last_login_time")
  private Long lastLoginTime;

  @Column(name = "is_suspend")
  private boolean isSuspend;


  @Column(name = "audio_num")
  private Integer audioNum;


}
