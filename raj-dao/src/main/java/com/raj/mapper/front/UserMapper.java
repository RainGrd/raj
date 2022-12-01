package com.raj.mapper.front;

import com.raj.entity.front.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【t_user(用户信息)】的数据库操作Mapper
* @createDate 2022-11-19 08:49:53
* @Entity com.raj.entity.front.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




