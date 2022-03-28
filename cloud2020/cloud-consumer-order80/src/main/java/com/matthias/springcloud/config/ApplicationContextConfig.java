package com.matthias.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //赋予RestTemplate负载均衡能力，测试自己手写的lb是否成功，先注释
//    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
