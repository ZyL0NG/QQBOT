package com.hxyy.ps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hxyy.ps.mapper")
public class PsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PsApplication.class,args);
    }
}
