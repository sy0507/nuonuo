package com.example.nuonuo.service;

public interface MailService {

  /**
   *
   * @param to 收件人
   * @param subject 主题
   * @param content 内容
   */
  void sendSimpleMail(String to, String subject, String content);
  void sendResetEmail(String email);

}
