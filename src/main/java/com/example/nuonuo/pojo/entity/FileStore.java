package com.example.nuonuo.pojo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "file_store")
@Getter
@Setter
public class FileStore {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "disk_name")
  private String diskName;

  @Column(name = "real_name")
  private String realName;
}
