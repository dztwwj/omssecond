package com.aaa.oms;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * className:SpringBootMainApplication
 * discription:启动类
 * * author:LiuQian
 * createTime:2018-12-10 14:43:07
 */
@SpringBootApplication
@MapperScan("com.aaa.oms.dao")
public class SpringBootMainApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringBootMainApplication.class);
    }
}
