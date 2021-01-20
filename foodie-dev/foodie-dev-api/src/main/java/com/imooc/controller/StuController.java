package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuController {
    @Autowired
    private StuService stuService;

    @GetMapping("/getStu")
    public Object getStu(int id){
        return stuService.getStuById(id);
    }

    @PostMapping("/deleteStu")
    public Object deleteStu(int id){
        stuService.deleteStuById(id);
        return "OK";
    }

    @PostMapping("")
    public Object updateStu(){
        Stu stu = new Stu();
        stu.setName("李四");
        stu.setAge(32);
        stuService.updateStuById(stu,1203);
        return "OK";
    }

    @PostMapping("/saveStu")
    public Object saveStu(){
        Stu stu = new Stu();
        stu.setName("李四");
        stu.setAge(32);
        stuService.saveStu(stu);
        return "OK";
    }


}
