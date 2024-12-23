package cn.edu.nhic.nhtj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("cn.edu.nhic.nhtj.tree.mapper")
@EnableFeignClients
@EnableSwagger2
public class TreeApp {
    public static void main(String[] args) {
        SpringApplication.run(TreeApp.class, args);
        System.out.println("success");
    }
}
