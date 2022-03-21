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
@Table(name = "follow")
public class Follow {
    @Id
    private Integer id;

    private Integer uid;
    @Column(name = "follow_id")
    private Integer followId;
}
