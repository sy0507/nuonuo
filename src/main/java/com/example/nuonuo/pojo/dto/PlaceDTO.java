package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class PlaceDTO {
    private String sname;

    private  String address;
    private Double longitude;

    private  Double latitude;

    private String need;

    @Column(name = "center_id")
    private Integer centerId;
}
