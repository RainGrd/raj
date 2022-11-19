package com.raj.front.service;

import com.raj.Vo.Result;
import com.raj.entity.front.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lenovo
* @description 针对表【t_user(用户信息)】的数据库操作Service
* @createDate 2022-11-19 08:49:53
*/
public interface UserService extends IService<User> {

    Result login(User user);
}
