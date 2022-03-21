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
@Table(name = "collect_info")
public class CollectInfo {
    @Id
    private Integer id;


    private Integer uid;

    @Column(name = "audio_id")
    private Integer audioId;
}
