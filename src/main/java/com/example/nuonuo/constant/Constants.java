package com.example.nuonuo.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {
  public static final String APK_VERSION_OS_ANDROID = "Android";

  public static final String APK_VERSION_OS_IOS = "iOS";

  public static final String PROFILE_DEV = "dev";

  public static final String PROFILE_TEST = "test";

  public static final String PROFILE_PROD = "prod";

  public static final int ORDERED_NORMAL_PRECEDENCE = 1000;

  /**
   * 项目默认字符编码
   */
  public static final Charset DEFAULT_CHATSET = StandardCharsets.UTF_8;

  /**
   * 手机号正则表达式
   */
  public static final String REGEX_PHONE_NUMBER = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";

  /**
   * 用户登录密码的正则表达式
   */
  public static final String REGEX_USER_LOGIN_PASSWORD = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z\\W].*)(?=.*[0-9\\W].*).{6,20}$";

  public static final int SMS_CODE_SIZE = 6;

  /**
   * 未认证用户等级
   */
  public static final int USER_LEVEL_UNAUTHORIZED = 0;

  /**
   * 一级用户等级
   */
  public static final int USER_LEVEL_ONE = 1;

  /**
   * 二级用户等级
   */
  public static final int USER_LEVEL_TWO = 2;

  /**
   * 普通管理员等级
   */
  public static final int USER_LEVEL_ACITIVITY_PUBLISHER = 3;
  /**
   * 校友会管理员等级
   */
  public  static  final  int USER_LEVEL_ALUMNI_ADMIN=4;
  /**
   * 校友总会管理员等级
   */
  public static  final  int USER_LEVEL_ALUMNI_ASSOCIATION_ADMIN=5;
  /**
   * 超级管理员等级
   */
  public static final int USER_LEVEL_ADMIN = 6;
}
