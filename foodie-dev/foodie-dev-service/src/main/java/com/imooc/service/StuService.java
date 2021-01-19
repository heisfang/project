package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {

    public Stu getStuById(int id);

    public void updateStuById(Stu stu,int id);

    public void deleteStuById(int id);

    public void saveStu(Stu stu);
}
