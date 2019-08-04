package cn.mybatistest.service.impl;

import cn.mybatistest.dao.StudentDao;
import cn.mybatistest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentDao studentDao;


    @Override
    public String getStudentById(String sno) {
        return studentDao.getStudentById(sno);
    }
}
