package com.example.demo.config;

import com.example.demo.util.SynchronizedByKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SynchronizedByKey synchronizedByKey() {
        return new SynchronizedByKey();
    }


}
