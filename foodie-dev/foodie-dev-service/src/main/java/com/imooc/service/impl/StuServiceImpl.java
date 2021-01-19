package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;


    @Override
    public Stu getStuById(int id) {

        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStuById(Stu stu, int id) {
        stuMapper.updateByExampleSelective(stu,id);
    }

    @Override
    public void deleteStuById(int id) {
        stuMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void saveStu(Stu stu) {
        stu = new Stu();
        stu.setAge(23);
        stu.setName("张三");
        stuMapper.insert(stu);
    }
}
