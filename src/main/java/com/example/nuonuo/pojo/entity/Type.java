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
@Table(name = "audio_type")
public class Type {
    @Id
    @Column(name = "audio_type_id")
    private Integer audioTypeId;

    @Column(name = "audio_type")
    private String audioType;
}
