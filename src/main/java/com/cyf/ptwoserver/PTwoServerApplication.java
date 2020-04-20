package com.cyf.ptwoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cyf.ptwoserver")
public class PTwoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PTwoServerApplication.class, args);
    }

}
