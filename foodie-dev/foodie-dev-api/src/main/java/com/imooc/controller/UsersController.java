package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.StuService;
import com.imooc.service.UsersService;
import com.imooc.utils.IMOOCJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/userNameIseExist")
    public IMOOCJSONResult getStu(@RequestParam String username){
        if(StringUtils.isEmpty(username)){
            return IMOOCJSONResult.errorMsg("用户名为空");
        }
        boolean isExist = usersService.userIsExist(username);
        if (isExist){
            return IMOOCJSONResult.errorMsg("用户已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @PostMapping("/saveUser")
    public IMOOCJSONResult saveUser(@RequestBody UsersBO usersBO){
        if(StringUtils.isEmpty(usersBO.getUsername())
                ||StringUtils.isEmpty(usersBO.getPassword())
                ||StringUtils.isEmpty(usersBO.getConfirmPassword())){
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        boolean b = usersService.userIsExist(usersBO.getUsername());
        if(b){
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }

        if(!usersBO.getPassword().equals(usersBO.getConfirmPassword())){
            return IMOOCJSONResult.errorMsg("密码错误");
        }

        if(usersBO.getPassword().length()<8){
            return IMOOCJSONResult.errorMsg("密码低于8位");
        }

        return IMOOCJSONResult.ok(usersService.saveUser(usersBO));
    }



}
