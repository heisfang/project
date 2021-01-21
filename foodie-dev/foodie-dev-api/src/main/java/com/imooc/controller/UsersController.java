package com.imooc.controller;

import com.imooc.pojo.Stu;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.StuService;
import com.imooc.service.UsersService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "注册登录",tags = {"用于注册登录的相关文档"})
@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
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

    @ApiOperation(value = "注册用户",notes = "用户注册",httpMethod = "POST")
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
