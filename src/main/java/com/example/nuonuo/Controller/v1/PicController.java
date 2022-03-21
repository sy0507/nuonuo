package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.dto.PicDTO;
import com.example.nuonuo.service.PicService;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/pic")
@Slf4j
@Validated
public class PicController {
    private final PicService picService;

    public PicController(PicService picService) {
        this.picService = picService;
    }


    @PostMapping("addPic")
    public WebAsyncTask<Object> addPic(@RequestBody @Validated PicDTO picDTO){
        return new WebAsyncTask<>(()->{
           picService.addPic(picDTO);
           return JsonResult.ok();
        });
    }


    @GetMapping("List")
    public WebAsyncTask<Object> getList(){
        return new WebAsyncTask<>(()->JsonResult.ok(picService.getList()));
    }

    @GetMapping("/show/{id}")
    public WebAsyncTask<Object> getPic(@PathVariable Integer id){
        return new WebAsyncTask<>(()->JsonResult.ok(picService.getPic(id)));

    }
}
