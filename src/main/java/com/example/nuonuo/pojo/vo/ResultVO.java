package com.example.nuonuo.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class ResultVO {
    private Integer id;
    @Column(name = "car_id")
    private  Integer carId;

    @Column(name = "full_load_rate")
    private Double fullLoadRate;

    private Double mileage;


    private String route;
}
