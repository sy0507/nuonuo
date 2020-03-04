package com.example.nuonuo.Controller.v1;


import com.example.nuonuo.pojo.dto.CarDTO;
import com.example.nuonuo.service.CarService;
import com.example.nuonuo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
@RequestMapping("/car")
@Slf4j
@Validated
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public WebAsyncTask<Object> addCar(@RequestBody @Validated CarDTO carDTO){
        return new WebAsyncTask<>(() -> {
            carService.addCar(carDTO);
            return JsonResult.ok();
        });
    }

}
