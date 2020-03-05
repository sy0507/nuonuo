package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "result")
public class Result {
    @Id
    private Integer id;
    @Column(name = "car_id")
    private  Integer carId;

    @Column(name = "full_load_rate")
    private Double fullLoadRate;

    private Double mileage;


    private String route;
}
