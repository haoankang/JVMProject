package ank.hao.multids.controller;

import ank.hao.multids.domain.Student;
import ank.hao.multids.domain.StudentExample;
import ank.hao.multids.mapper.StudentMapper;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/student/{db}")
    public Object getStudents(@PathVariable("db") String db){
        StudentExample studentExample = new StudentExample();
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        return JSON.toJSONString(studentList);
    }

}
