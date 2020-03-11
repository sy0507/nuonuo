package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class Car {
    @Column(name = "car_id")
    private Integer carId;

    private Integer num;

    private Double weight;

}
