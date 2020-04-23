package com.yipeng.baseservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: yibingzhou
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.yipeng.framework","com.yipeng.baseservice"})
@MapperScan(basePackages = {"com.yipeng.baseservice.config.mapper"})
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
