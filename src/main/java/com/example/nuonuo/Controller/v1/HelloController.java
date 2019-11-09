package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/v1")
@Slf4j
public class HelloController {
  @RequestMapping(method = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PUT})
  public WebAsyncTask<Object> sayHello() {
    return new WebAsyncTask<>(() -> {
      log.info("reach index with root path.");
      return JsonResult.ok();
    });
  }
}
