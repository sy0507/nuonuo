package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.CenterCarDTO;
import com.example.nuonuo.pojo.dto.CenterDTO;
import com.example.nuonuo.pojo.dto.DistanceDTO;
import com.example.nuonuo.pojo.dto.PutUserProfileDTO;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.CenterService;
import com.example.nuonuo.token.Token;
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
    public WebAsyncTask<Object> ownCar(@PathVariable Integer centerId, @RequestBody CenterCarDTO centerCarDTO){

        return new WebAsyncTask<>(() -> {
            centerService.own(centerId,centerCarDTO);
            return JsonResult.ok();
        });
    }
    @GetMapping("/list")
    public WebAsyncTask<Object> getInfo(){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.getCenterInfoVo()));
    }

    @GetMapping("/owncar/{centerId}")
    public WebAsyncTask<Object> getOwnCar(@PathVariable Integer centerId){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.getOwnCarVo(centerId)));
    }

    @PutMapping("/modify")
    public WebAsyncTask<Object> modify(Integer id,@RequestBody @Validated CenterDTO centerDTO) {
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.modify(id, centerDTO)));
    }

    @PutMapping("/modifycar")
    public WebAsyncTask<Object> modifycar(Integer carId,Integer centerId,@RequestBody @Validated CenterCarDTO centerCarDTO) {
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.modifycar(carId,centerId,centerCarDTO)));
    }
    @GetMapping("/info")
    public WebAsyncTask<Object> getInfo(Integer id){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.getInfo(id)));
    }
    @DeleteMapping("/car")
    public WebAsyncTask<Object> delete(Integer carId,Integer centerId) {
        return new WebAsyncTask<>(() -> {
            centerService.delete(carId,centerId);
            return JsonResult.ok();
        });
    }

    @DeleteMapping("/place/{id}")
    public WebAsyncTask<Object> deletecenter(@PathVariable Integer id) {
        return new WebAsyncTask<>(() -> {
            centerService.deletecenter(id);
            return JsonResult.ok();
        });
    }



    @PostMapping("/distance/{centerId}")
    public WebAsyncTask<Object> distance(@PathVariable Integer centerId){
        return new WebAsyncTask<>(() -> {
            centerService.distance(centerId);
            return JsonResult.ok();
        });
    }
    @GetMapping("/execute/{centerId}")
    public WebAsyncTask<Object> execute(@PathVariable Integer centerId){
        return new WebAsyncTask<>(() -> JsonResult.ok(centerService.execute(centerId)));
    }
}
