package com.fido.baseservice.config;

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
@ComponentScan(basePackages = {"com.fido.framework","com.fido.baseservice"})
@MapperScan(basePackages = {"com.fido.baseservice.config.mapper"})
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
