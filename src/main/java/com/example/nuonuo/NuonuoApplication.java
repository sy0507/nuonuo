package com.example.nuonuo;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.example.nuonuo.mapper")
@EnableCaching
@EnableScheduling
@EnableAsync
public class NuonuoApplication {

  public static void main(String[] args) {
    SpringApplication.run(NuonuoApplication.class, args);
  }

}
