package com.redis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisDemoApplication.class, args);

        System.out.println("启动完成...");
    }

}
