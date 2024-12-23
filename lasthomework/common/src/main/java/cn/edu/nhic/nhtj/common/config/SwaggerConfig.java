package cn.edu.nhic.nhtj.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    Boolean swaggerEnabled = true;

    String headerName="token";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled)//true
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                //.paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        //配置header头1
        ApiKey token_access = new ApiKey(headerName, headerName, "header");
        apiKeyList.add(token_access);
        //配置header头2
        //ApiKey token_access2 = new ApiKey( headerName2, headerName2, "header");
        //apiKeyList.add(token_access2);
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContextList = new ArrayList<>();
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        //为每个api添加请求头
        securityReferenceList.add(new SecurityReference(headerName, scopes()));
        //以此类推
        //securityReferenceList.add(new SecurityReference(headerName2, scopes()));
        securityContextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferenceList)
                .forPaths(PathSelectors.any())
                .build()
        );
        return securityContextList;
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")};//作用域为全局
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("南华图鉴")
                //创建人
                .contact(new Contact("nhliuws", "http://www.nhtj.com", "abc@nhcar.com"))
                .version("1.0")
                .description("不需要描述")
                .build();
    }
}
