package com.fibbery.springboot.starter.dubbo;

import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.annotation.Service;
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
@ConditionalOnClass(Service.class)
@EnableConfigurationProperties(DubboProperties.class)
public class DubboProviderAutoConfiguration {
    @Autowired
    private DubboProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ProviderConfig providerConfig() {
        ProviderConfig config = properties.getProvider();
        config.setApplication(properties.getApplication());
        config.setRegistry(properties.getRegistry());
        config.setProtocol(properties.getProtocol());
        log.info("provider config load correct");
        return config;
    }
}
