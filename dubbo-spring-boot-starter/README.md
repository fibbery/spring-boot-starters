# dubbo-spring-boot-starter

## 使用方法

* 添加依赖

  ```xml
  <dependency>
    <groupId>com.fibbery.springboot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
  </dependency>
  ```

  ​

* 在application.properties中添加配置

  ```properties
  # 应用服务信息
  dubbo.scan-package=com.fibbery.demo.provider
  dubbo.application.name=demo-provider

  # 注册服务信息
  dubbo.registry.register=true
  dubbo.registry.protocol=zookeeper
  dubbo.registry.address=10.1.31.119:2181

  # 暴露接口服务信息(consumer端可不录入)
  dubbo.protocol.name=dubbo
  dubbo.protocol.port=20880

  # provider端配置
  dubbo.provider.* = xxxxx

  # consumer端配置
  dubbo.consumer.* = xxxxx

  # 需要进行偏向连接可进行如下配置
  dubbo.consumer.loadbalance=prefer
  dubbo.consumer.parameters.perferIP= (ip列表，使用分号分割)
  ```

* 启动springboot程序