package com.example.nuonuo.pojo.vo;

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
public class TypeVO {
    @Id
    @Column(name = "audio_type_id")
    private Integer audioTypeId;

    @Column(name = "audio_type")
    private String audioType;
}
