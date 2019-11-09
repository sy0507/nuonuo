//package com.example.nuonuo.Controller.v1;
//
//
//import com.example.nuonuo.pojo.dto.MessageDTO;
//import com.example.nuonuo.pojo.entity.User;
//import com.example.nuonuo.service.MessageService;
//import com.example.nuonuo.token.Token;
//import com.example.nuonuo.util.JsonResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.async.WebAsyncTask;
//
//@RestController
//@RequestMapping("/v1/message")
//public class MessageController {
//  private  final MessageService messageService;
//
//  public MessageController(MessageService messageService) {
//    this.messageService = messageService;
//  }
//
//  /**
//   * 返回该用户所有留言
//   * @param user
//   * @return
//   */
//  @GetMapping("/list")
//  public WebAsyncTask<Object> query(@Token User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(messageService.getAll( user)));
//  }
//
//  /**
//   * 发送留言
//   */
//  @PostMapping("/session")
//  public WebAsyncTask<Object> create(@RequestBody @Validated MessageDTO messageDTO,
//                                     @Token User user) {
//    return new WebAsyncTask<>(() -> JsonResult.ok(messageService.insert(messageDTO, user)));
//  }
//
//}
