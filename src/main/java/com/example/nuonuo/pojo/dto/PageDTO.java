package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
    private Integer page = 1;
    private Integer pageSize = 9;
    private String order;
    private String sortBy;
}
