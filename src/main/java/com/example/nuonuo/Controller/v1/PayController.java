package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.service.PayService;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/pay")
@Slf4j
@Validated
public class PayController {
    private final PayService payService;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping("/toPay")
    public void pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestParam("audio_id") Integer audioId,@RequestParam("uid") Integer uid) throws IOException {
            String form = payService.pay(audioId, uid);
        httpResponse.setContentType( "text/html;charset=utf-8");
        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    @GetMapping("/pay_list/{uid}")
    public WebAsyncTask<Object> getPayListByUid(@PathVariable("uid") Integer uid){
        return new WebAsyncTask<>(()-> JsonResult.ok(payService.getPayByuid(uid)));
    }

    @GetMapping("/payList")
    public WebAsyncTask<Object> getPayList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(payService.getPayList()));
    }






}
