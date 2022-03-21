package com.example.nuonuo.Controller.v1;

import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.service.CarouselService;
import com.example.nuonuo.token.Token;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/carousel")
@Slf4j
@Validated
public class CarouselController {
    private final CarouselService carouselService;

    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }



    @GetMapping
    public WebAsyncTask<Object> getCarouselList(){
        return new WebAsyncTask<>(()-> JsonResult.ok(carouselService.getCarouselList()));
    }
}
