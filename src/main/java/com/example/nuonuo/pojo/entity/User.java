package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;

@Getter
@Setter
public class User {
  @Id
  @Column(name = "uid")
  private Integer uid;

  /**
   * 密码
   */

  private String password;
  /**
   * token
   */
  @Column(name = "accesstoken")
  private String accessToken;
  /**
   * 电话
   */
  private String phone;
  /**
   * 昵称
   */
  private String name;
  /**
   * 性别
   */
  private String sexual;

  /**
   * qq
   */
  private String qq;
  /**
   * 微信
   */
  @Column(name = "weixin")
  private String weixin;
  /**
   * 头像id
   */
  @Column(name = "head_pic_id")
  private Integer headPicId;

  private String email;



}
