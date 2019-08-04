package cn.mybatistest.Config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //必须存在
@EnableSwagger2 //必须存在  启动Swagger
@EnableWebMvc //必须存在
@ComponentScan("cn.mybatistest.controller")//必须存在  扫描的API Controller包
public class SwaggerConfig {

    @Bean  //创建一个Docket容器
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("学生信息查询系统")
                .select()
                .apis(RequestHandlerSelectors.any()) //.basePackage("net.zoneday.excel.controller")
                .paths(PathSelectors.any())
                // 不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*"))) //错误路径不监控
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("学生信息查询系统")
                .description("这是一个利用Swagger写的一个学生信息查询系统的API管理工具")
                .termsOfServiceUrl("http://www.sdgzs.com")
                .contact(new Contact("郑晖", "http://www.sdgzs.com", "8042965@sdgzs.com"))
                .license("Apache License 2.0")
                .licenseUrl("")
                .version("1.0.0")
                .build();
    }


}
