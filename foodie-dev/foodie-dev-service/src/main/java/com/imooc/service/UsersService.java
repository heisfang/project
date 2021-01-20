package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;

public interface UsersService {

    /**
     * 判断用户名是否存在
     */
    public boolean userIsExist(String userName);

    /**
     * 添加用户
     */
    public Users saveUser(UsersBO user);

    /**
     * 用户userName获取user
     */
    public Users getUserByName(String userName);

}
