package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.CarMapper;
import com.example.nuonuo.pojo.dto.CarDTO;
import com.example.nuonuo.pojo.entity.Car;
import com.example.nuonuo.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
  private final CarMapper carMapper;

    public CarServiceImpl(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public void addCar(CarDTO carDTO) {
        Car car=new Car();
        BeanUtils.copyProperties(carDTO,car);
        carMapper.insertSelective(car);



    }
}
