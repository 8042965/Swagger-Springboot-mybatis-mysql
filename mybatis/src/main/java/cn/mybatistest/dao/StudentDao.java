package cn.mybatistest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDao {

    public String  getStudentById(String sno);

}
