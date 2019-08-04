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
    public String getStudentById(@ApiParam(value = "请输入学生学号",required = true)String sno){
        String Sname = studentService.getStudentById(sno);
        return  Sname;
    }



}
