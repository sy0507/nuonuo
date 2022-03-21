package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.AdminDTO;
import com.example.nuonuo.pojo.dto.AuditDTO;
import com.example.nuonuo.pojo.dto.SuspendDTO;
import com.example.nuonuo.pojo.dto.UserLoginDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.AdminService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/manage")
@Slf4j
@Validated
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public WebAsyncTask<Object> login(@RequestBody @Validated AdminDTO dto){
        return new WebAsyncTask<>(() -> JsonResult.ok(adminService.login(dto)));
    }

    @PostMapping("/resetPwd/{uid}")
    public WebAsyncTask<Object> resetPwd(@PathVariable("uid") Integer uid) {
        return new WebAsyncTask<>(() -> {
            adminService.resetPwd(uid);
            return JsonResult.ok();
        });
    }
    @PostMapping("/resetName/{uid}")
    public WebAsyncTask<Object> resetName(@PathVariable("uid") Integer uid) {
        return new WebAsyncTask<>(() -> {
            adminService.resetName(uid);
            return JsonResult.ok();
        });
    }

    @PostMapping("/suspend")
    public WebAsyncTask<Object> suspend(@RequestBody @Validated SuspendDTO dto){
        return new WebAsyncTask<>(() -> {
            adminService.suspend(dto);
            return JsonResult.ok();
        });
    }
    @PostMapping("/unsuspend/{uid}")
    public WebAsyncTask<Object> unsuspend(@PathVariable("uid") Integer uid){
        return new WebAsyncTask<>(() -> {
            adminService.unsuspend(uid);
            return JsonResult.ok();
        });
    }

    @GetMapping("/suspend_list/{uid}")
    public WebAsyncTask<Object> getTypeList(@PathVariable("uid") Integer uid){
        return new WebAsyncTask<>(()-> JsonResult.ok(adminService.getSuspendList(uid)));
    }

    @GetMapping("/user_list")
    public WebAsyncTask<Object> getUserList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(adminService.getUserList()));
    }

    @GetMapping("/audio_list")
    public WebAsyncTask<Object> getAudioList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(adminService.getAudioList()));
    }


    @PostMapping("/audit")
    public WebAsyncTask<Object> audit(@RequestBody @Validated AuditDTO dto){
        return new WebAsyncTask<>(() -> {
            adminService.audit(dto);
            return JsonResult.ok();
        });
    }












}
