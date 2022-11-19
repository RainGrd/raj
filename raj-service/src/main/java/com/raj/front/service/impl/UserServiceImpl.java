package com.raj.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.entity.front.User;
import com.raj.front.service.UserService;
import com.raj.front.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【t_user(用户信息)】的数据库操作Service实现
* @createDate 2022-11-19 08:49:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




