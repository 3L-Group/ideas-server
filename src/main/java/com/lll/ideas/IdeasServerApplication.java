package com.lll.ideas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lll.ideas.dao")
public class IdeasServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdeasServerApplication.class, args);
    }

}
