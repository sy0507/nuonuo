package com.example.nuonuo.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@ToString
public class AuditDTO {
    @Column(name = "audio_id")
    private Integer audioId;

    private String reason;

    private Integer status;

//    @Column(name = "audit_time")
//    private Date auditTime;

    @Column(name = "admin_id")
    private Integer adminId;


}
