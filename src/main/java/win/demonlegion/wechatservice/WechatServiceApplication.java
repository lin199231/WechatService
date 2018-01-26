package win.demonlegion.wechatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"win.demonlegion"})
@SpringBootApplication
public class WechatServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatServiceApplication.class, args);
    }
}
