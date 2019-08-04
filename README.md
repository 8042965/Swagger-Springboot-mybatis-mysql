# Swagger-Springboot-mybatis-mysql
Swagger+Springboot+mybatis+mysql。使用Swagger写的学生信息管理系统的API管理工具


@[toc]
# 此项目说明
此项目是我闲着的时候，整理出来的。因为我主要是做后端的，接口可能测试比较麻烦，所以就想起来了使用Swagger来管理API，然后还起到了测试的作用，还省得费劲去整理API文档了。一举两得，灰常的完美鸭！

# 环境

maven

SpringBoot



注：下文全是在SpringBoot2.x和已有Maven环境的基础上进行做的。

如果没有请移步：

[SpringBoot一1（初次使用）+HelloWord](https://blog.csdn.net/qq_17623363/article/details/88859014 "SpringBoot一1（初次使用）+HelloWord")


------

# 源代码

[本项目源代码](https://github.com/8042965/Swagger-Springboot-mybatis-mysql "源代码")

[SpringBoot一1（初次使用）+HelloWord](https://blog.csdn.net/qq_17623363/article/details/88859014 "SpringBoot一1（初次使用）+HelloWord")

[Maven环境配置-必会](https://blog.csdn.net/qq_17623363/article/details/88858907 "Maven环境配置-必会")



------



# 一、引入Swagger依赖

```xml
   <!-- 引入swgger相关依赖       -->


<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.6.1</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.6.1</version>
</dependency>

<dependency>
    <groupId>org.codehaus.jackson</groupId>
    <artifactId>jackson-core-asl</artifactId>
    <version>1.9.13</version>
</dependency>
```





为了让界面漂亮引入bootstrap-ui

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.9.1</version>
</dependency>
```



原始的界面：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102602257.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)

引入bootstrap-ui后的：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102622639.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)




此工程的目录结构：

![在这里插入图片描述](https://img-blog.csdnimg.cn/2019080410264516.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)



# 二、配置Swagge配置文件

在项目下的config包下创建SwaggerConfig.java文件

用来配置Swagger的信息的，例如：

项目的简介

做着

版本

服务URL

分组名称等

这些信息会在主页进行显示，如图所示：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102703190.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)


SwaggerConfig.java

```java
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

```



在项目下的config包下创建WebMvcConfig.java文件

此文件是用来配置UI的访问界面的路径和静态资源的配置，如果不配置这个，是无法访问UI界面的。



```java
package cn.mybatistest.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //原始的swagger  UI界面的配置
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        //引入BS后的的swagger  UI界面的配置
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}

```



# 三、编写Controller层

以查询学号查询学生姓名为例



这里只展示出COntroller层的代码，如果想要其他的，请自行下载源代码工程文件。

```java
package cn.mybatistest.controller;

import cn.mybatistest.dao.StudentDao;
import cn.mybatistest.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
@Api(value = "查询学生信息")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 通过学号查询学生姓名
     * @param sno
     * @return
     */
    @PostMapping("/getStudentById")
    @ResponseBody
    @ApiOperation("使用学号查询学生名字")
    public String getStudentById(@ApiParam(value = "请输入学生学号",required = true) @RequestParam(name="sno")String sno){
        String Sname = studentService.getStudentById(sno);
        return  Sname;
    }



}

```



# 四、数据库结构和数据

```sql
/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : test5

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-08-04 10:03:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Sno` varchar(12) NOT NULL COMMENT '学号',
  `Sdept` varchar(20) NOT NULL,
  `Sname` varchar(20) NOT NULL,
  `Clbum` varchar(20) NOT NULL,
  `Sex` varchar(4) NOT NULL,
  `Age` int(3) NOT NULL,
  `DormID` varchar(20) NOT NULL,
  `PoiticsStatus` varchar(20) NOT NULL,
  `HomeAddress` varchar(50) NOT NULL,
  `Rest` varchar(200) NOT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201711106044', '计算机学院', 'zhenghui', '网络171C', '男', '21', '竹一630', '共青团员', '', '这是计算机学院的人');
INSERT INTO `student` VALUES ('201711106003', '计算机学院', 'wangwu', '网络171C', '男', '21', '竹三412', '党员', '济南市', '这个也是好人');
INSERT INTO `student` VALUES ('201811101001', '机电学院', '张志洋', '机电181', '男', '18', '竹三111', '', '', '');
INSERT INTO `student` VALUES ('201611101001', '艺术学院', '李小明', '技术161', '女', '0', '松四504', '', '', '');

```



# 五、测试结果

## 测试1、原始Ui界面：

输入：

http://localhost:8080/swagger-ui.html#

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102725513.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)



## 测试2、BS的UI：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102739298.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190804102750348.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzE3NjIzMzYz,size_16,color_FFFFFF,t_70)
