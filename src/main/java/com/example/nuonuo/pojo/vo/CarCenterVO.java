package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class CarCenterVO {
    @Column(name = "center_id")
    private Integer centerId;
    @Column(name = "car_id")
    private Integer carId;
    private Integer num;
    private Double weight;
}
