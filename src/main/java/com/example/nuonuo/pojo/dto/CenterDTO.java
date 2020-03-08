package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
public class CenterDTO {

    private String sname;


    private Double longitude;

    private  Double latitude;


    private String address;

//    @Column(name = "car_id")
//    private List<Integer> carId;

    private List<Integer> num;

    private List<Double> weight;



}
