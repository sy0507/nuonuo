package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class AdminDTO {
    @Column(name = "admin_name")
    private String adminName;

    private String password;
}
