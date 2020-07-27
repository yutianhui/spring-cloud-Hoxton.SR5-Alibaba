package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName SeataOrderMain8052
 * @Author Yutian Hui
 * @date 2020.07.25 19:22
 */
@SpringBootApplication
@MapperScan("com.study.mybatismbg.mappers")
public class TestMain5555 {

    public static void main(String[] args) {
        SpringApplication.run(TestMain5555.class,args);
    }

}
