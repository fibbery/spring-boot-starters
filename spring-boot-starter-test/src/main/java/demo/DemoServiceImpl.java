package demo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author fibbery
 * @date 18/3/13
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Override
    public void sayHello() {
        System.out.printf("hello");
    }
}
