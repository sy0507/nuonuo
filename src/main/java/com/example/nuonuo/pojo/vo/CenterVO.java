package com.example.nuonuo.pojo.vo;

import com.example.nuonuo.pojo.entity.Car;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
public class CenterVO {
    @Id
    @Column(name = "center_id")
    private Integer centerId;

    private String sname;

    private Double longitude;

    private  Double latitude;

    private String need;

    private String address;

    private List<Car> listCar;

}
