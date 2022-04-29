package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {

    private Integer page;

    private Integer pageSize;

    private String order;

    private String sortBy;
}
