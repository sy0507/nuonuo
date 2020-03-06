package com.example.nuonuo.pojo.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class CenterDTO {

    private String sname;


    private Double longitude;

    private  Double latitude;

    private String need;

    private String address;

}
