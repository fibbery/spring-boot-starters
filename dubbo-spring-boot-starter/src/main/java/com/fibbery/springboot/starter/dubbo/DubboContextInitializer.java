package com.fibbery.springboot.starter.dubbo;

import com.alibaba.dubbo.config.spring.AnnotationBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class DubboContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        if (activeProfiles.length == 0) {
            activeProfiles = new String[]{"default"};
            log.info("auto config : spring active profile is default");
        }

        // 初始化Dubbo直连文件
        initResolveEnv(activeProfiles[0]);

        //注入annotationBean
        registerAnnotationBean(applicationContext);
    }

    private void initResolveEnv(String activeProfile) {
        String dubboResolveFile = "resolve-dubbo-" + activeProfile + ".properties";
        InputStream inputStream = DubboProperties.class.getClassLoader().getResourceAsStream(dubboResolveFile);
        if (inputStream == null) {
            return;
        }
        try {
            log.info("auto configuration ==================> use resolve file:{}", dubboResolveFile);
            Properties properties = new Properties();
            properties.load(inputStream);
            for (String name : properties.stringPropertyNames()) {
                System.setProperty(name, properties.getProperty(name));
                log.info("auto configuration ==================> {}={}", name, properties.getProperty(name));
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    private void registerAnnotationBean(ConfigurableApplicationContext context) {
        String scanPackage = context.getEnvironment().getProperty("dubbo.scan-package");
        if (StringUtils.isEmpty(scanPackage)) {
            return;
        }
        log.info("auto configuration ==================> dubbo scan initializer [package:{}]",scanPackage);
        AnnotationBean annotationBean = BeanUtils.instantiate(AnnotationBean.class);
        annotationBean.setPackage(scanPackage);
        annotationBean.setApplicationContext(context);
        context.addBeanFactoryPostProcessor(annotationBean);
        context.getBeanFactory().addBeanPostProcessor(annotationBean);
        context.getBeanFactory().registerSingleton("annotationBean", annotationBean);
    }


}
