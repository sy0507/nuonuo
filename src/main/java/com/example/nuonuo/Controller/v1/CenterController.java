package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.CenterCarDTO;
import com.example.nuonuo.pojo.dto.CenterDTO;
import com.example.nuonuo.pojo.dto.DistanceDTO;
import com.example.nuonuo.service.CenterService;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/center")
@Slf4j
@Validated
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping("/add")
    public WebAsyncTask<Object> addCenter(@RequestBody @Validated CenterDTO centerDTO) {
        return new WebAsyncTask<>(() -> {
            centerService.add(centerDTO);
            return JsonResult.ok();
        });
    }

    @PostMapping("/own/{centerId}")
    public WebAsyncTask<Object> ownCar(@PathVariable("centerId") Integer id, @RequestBody CenterCarDTO centerCarDTO){

        return new WebAsyncTask<>(() -> {
            centerService.own(id,centerCarDTO);
            return JsonResult.ok();
        });
    }
    @GetMapping("/list")
    public WebAsyncTask<Object> getInfo(){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.getCenterInfoVo()));
    }

    @GetMapping("/owncar/{centerId}")
    public WebAsyncTask<Object> getOwnCar(@PathVariable("centerId") Integer id){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.getOwnCarVo(id)));
    }

    @PostMapping("/distance")
    public WebAsyncTask<Object> distance(){
        return new WebAsyncTask<>(() -> {
            centerService.distance();
            return JsonResult.ok();
        });
    }
}
