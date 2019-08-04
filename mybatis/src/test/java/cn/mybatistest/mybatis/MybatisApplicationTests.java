package cn.mybatistest.mybatis;

import cn.mybatistest.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    StudentService studentService;

    @Test
    public void contextLoads() {
        String studentSname = studentService.getStudentById("201711106044");
        System.out.println(studentSname);
    }

}
