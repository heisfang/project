package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.UsersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@Api(value = "注册登录", tags = {"用于注册登录的相关文档"})
@RestController
@RequestMapping("passport")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult getStu(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return IMOOCJSONResult.errorMsg("用户名为空");
        }
        boolean isExist = usersService.userIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户已存在");
        }
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "注册用户", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public IMOOCJSONResult saveUser(@RequestBody UsersBO usersBO, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(usersBO.getUsername())
                || StringUtils.isEmpty(usersBO.getPassword())
                || StringUtils.isEmpty(usersBO.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        boolean b = usersService.userIsExist(usersBO.getUsername());
        if (b) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }

        if (!usersBO.getPassword().equals(usersBO.getConfirmPassword())) {
            return IMOOCJSONResult.errorMsg("密码错误");
        }

        if (usersBO.getPassword().length() < 8) {
            return IMOOCJSONResult.errorMsg("密码低于8位");
        }


        Users u = setNullProperty(usersService.saveUser(usersBO));
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(u), true);

        return IMOOCJSONResult.ok(u);
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UsersBO usersBO, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(usersBO.getUsername())
                || StringUtils.isEmpty(usersBO.getPassword())) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        Users users = usersService.getUserByName(usersBO.getUsername());
        if (users == null) {
            return IMOOCJSONResult.errorMsg("用户名不存在");
        }
        try {
            String password = MD5Utils.getMD5Str(usersBO.getPassword());
            if (!users.getPassword().equals(password)) {
                return IMOOCJSONResult.errorMsg("密码错误");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Users u = setNullProperty(users);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(u), true);

        return IMOOCJSONResult.ok(u);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,"user");
        return IMOOCJSONResult.ok();
    }

    public Users setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
        return user;
    }


}
