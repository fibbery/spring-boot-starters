package com.fibbery.springboot.starter.dubbo;

import com.alibaba.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fibbery
 * @date 18/3/13
 */
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
    private ApplicationConfig application = new ApplicationConfig();

    private RegistryConfig registry = new RegistryConfig();

    private ProtocolConfig protocol = new ProtocolConfig();

    private ProviderConfig provider = new ProviderConfig();

    private ConsumerConfig consumer = new ConsumerConfig();

    public ApplicationConfig getApplication() {
        return application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        return registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public ProviderConfig getProvider() {
        return provider;
    }

    public void setProvider(ProviderConfig provider) {
        this.provider = provider;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }
}
