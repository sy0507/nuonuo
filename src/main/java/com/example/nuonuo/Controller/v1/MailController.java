package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.service.MailService;
import com.example.nuonuo.util.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping(value = "/mail")
public class MailController {


  private  final MailService mailService;



  public MailController(MailService mailService) {
    this.mailService = mailService;
  }


  @Value("${spring.mail.username}")
  private String from;

  /**
   * 发送忘记密码邮件请求，每日申请次数不超过5次，每次申请间隔不低于1分钟
   *
   * @param email
   */
  @GetMapping("/sendResetEmail/{email}")
  public WebAsyncTask<Object> sendResetEmail(@PathVariable String email) {
    System.out.println(1);
    return new WebAsyncTask<>(()->{
      mailService.sendResetEmail(email);
      return JsonResult.ok();
    });
  }
}
