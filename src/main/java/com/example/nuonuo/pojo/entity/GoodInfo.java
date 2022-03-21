package com.example.nuonuo.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "good_info")
public class GoodInfo {

    @Id
    private Integer id;


    private Integer uid;

    @Column(name = "audio_id")
    private Integer audioId;
}
