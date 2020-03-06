package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class Place {
    @Id
    @Column(name = "place_id")
    private Integer placeId;

    private String sname;

    private  String address;
    private Double longitude;

    private  Double latitude;

    private String need;

    @Column(name = "center_id")
    private Integer centerId;
}
