package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

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
  private String weixin;
  /**
   * 头像id
   */
  @Column(name = "head_pic_id")
  private Integer headPicId;

  /**
   * 头像地址
   */
  private String headPicUrl;
}
