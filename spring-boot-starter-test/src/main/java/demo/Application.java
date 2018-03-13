package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

/**
 * @author fibbery
 * @date 18/3/13
 */
@SpringBootApplication
public class Application {
    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new SpringApplicationBuilder().sources(Application.class).web(false).run(args);
        CountDownLatch closeLatch = context.getBean(CountDownLatch.class);
        closeLatch.await();
    }


}
