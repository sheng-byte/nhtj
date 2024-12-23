package cn.edu.nhic.nhtj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.edu.nhic.nhtj.admin.mapper")
@EnableSwagger2
public class AdminApp {
    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class, args);
        System.out.println("admin:success");
    }
}
