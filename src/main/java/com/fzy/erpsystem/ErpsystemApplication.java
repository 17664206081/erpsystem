package com.fzy.erpsystem;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ErpsystemApplication{

    public static void main(String[] args) {
        SpringApplication.run(ErpsystemApplication.class, args);
        log.info("---------------启动完成-------------------");
    }

}
