package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class CarCenterVO {
    @Id
    @Column(name = "center_id")
    private Integer centerId;
    @Id
    @Column(name = "car_id")
    private Integer carId;
    private Integer num;
    private Double weight;
}
