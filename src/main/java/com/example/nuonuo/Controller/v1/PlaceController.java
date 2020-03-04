package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.PlaceDTO;
import com.example.nuonuo.service.PlaceService;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/place")
@Slf4j
@Validated
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/add")
    public WebAsyncTask<Object> addCenter(@RequestBody @Validated PlaceDTO placeDTO) {
        return new WebAsyncTask<>(() -> {
            placeService.add(placeDTO);
            return JsonResult.ok();
        });
    }

    @GetMapping("/show")
    public WebAsyncTask<Object> show(){
        return new WebAsyncTask<>(() -> JsonResult.ok(placeService.getplaceInfoVo()));
    }
}
