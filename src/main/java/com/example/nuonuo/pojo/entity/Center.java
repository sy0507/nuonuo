package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@ToString
public class Center {
    @Id
    @Column(name = "center_id")
    private Integer centerId;

    private String sname;


    private Double longitude;

    private  Double latitude;

    private String need;
}
