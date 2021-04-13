package com.jq.mall.config;

import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan({"com.jq.mall.mbg.mapper", "com.jq.mall.dao"})
public class MyBatisConfig {
}
