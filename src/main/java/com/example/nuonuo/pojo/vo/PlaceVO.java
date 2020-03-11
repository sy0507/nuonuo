package com.example.nuonuo.pojo.vo;


import com.example.nuonuo.pojo.entity.Car;
import com.example.nuonuo.pojo.entity.Center;
import com.example.nuonuo.pojo.entity.CenterCar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
public class PlaceVO {
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

    private Center center;

    private List<CenterCar> carList;
}
