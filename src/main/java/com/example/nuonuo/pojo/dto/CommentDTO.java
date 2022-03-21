package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@ToString
public class CommentDTO {
    @Column(name = "audio_id")
    private Integer audioId;

    private String content;
}
