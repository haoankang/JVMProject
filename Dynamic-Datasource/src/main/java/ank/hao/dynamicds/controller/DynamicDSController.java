package ank.hao.dynamicds.controller;

import ank.hao.dynamicds.domain.StudentExample;
import ank.hao.dynamicds.dynamic.DDS;
import ank.hao.dynamicds.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;

@RestController
public class DynamicDSController {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping("/dynamic")
    @DDS(value = "db2")
    public Object demo1(){
        return JSON.toJSONString(studentMapper.selectByExample(new StudentExample()));
    }
}
