package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class CenterCarDTO {
    @Column(name = "car_id")
    private Integer carId;

    private Integer num;
}
