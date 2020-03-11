package com.example.nuonuo.pojo.vo;

import com.example.nuonuo.pojo.entity.Center;
import com.example.nuonuo.pojo.entity.Place;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@ToString
public class ResultVO {

    private Center center;

    private Place place;

    private String discharges;

}
