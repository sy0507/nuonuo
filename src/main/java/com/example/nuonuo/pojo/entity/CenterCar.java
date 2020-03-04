package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class CenterCar {
    @Column(name = "center_id")
    private Integer centerId;

    @Column(name = "car_id")
    private Integer carId;

    private Integer num;
}
