package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@Setter
@ToString
public class SearchDTO {
    @Column(name = "audio_id")
    private Integer audioId;

    @Column(name = "audio_name")
    private String audioName;

    private Integer uid;
}
