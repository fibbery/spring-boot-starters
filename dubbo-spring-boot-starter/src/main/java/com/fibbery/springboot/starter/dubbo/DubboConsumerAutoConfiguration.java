package com.fibbery.springboot.starter.dubbo;

import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fibbery
 * @date 18/3/13
 */
@Slf4j
@Configuration
@ConditionalOnClass(Reference.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboConsumerAutoConfiguration {

    @Autowired
    private DubboProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig config = properties.getConsumer();
        config.setApplication(properties.getApplication());
        config.setRegistry(properties.getRegistry());
        log.info("consumer config load correctly");
        return config;
    }
}
