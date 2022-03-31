package com.matthias.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.matthias.springcloud.dao"})
public class MyBatisConfig {
}
