package com.raj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.Vo.Result;
import com.raj.entity.front.User;
import com.raj.front.service.UserService;
import com.raj.front.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lenovo
 * @description 针对表【t_user(用户信息)】的数据库操作Service实现
 * @createDate 2022-11-19 08:49:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*根据邮件和验证码*/
//        userLambdaQueryWrapper.eq(User::getName,user.getName());
//        userLambdaQueryWrapper.eq(User::get,user.getName());
        userMapper.selectOne(null);
        return null;
    }
}




