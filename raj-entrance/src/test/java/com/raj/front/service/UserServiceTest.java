package com.raj.front.service;

import com.raj.entity.front.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserServiceTest
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/11/21 20:05
 * @Author RainGrd
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void getEmailCodeTest() {
        long startTime = System.currentTimeMillis();
        User user = new User();
        user.setEmail("duanronggui1224@163.com");
        userService.getEmailCode(user);
        long overTime = System.currentTimeMillis();
        System.out.println(overTime - startTime);
    }


    @Test
    void saveUserTest() {
        User user = new User();
        user.setName("RainGrd");
        user.setPhone("15873461176");
        user.setSex(0);
        user.setIdNumber("430481200512240371");
        user.setAvatar("");
        user.setStatus(1);
        user.setEmail("duanronggui1224@163.com");
        userService.saveUser(user);
    }

    @Test
    void loginTest() {
        Map<String, String> map = new HashMap<>();
        map.put("email", "duanronggui1224@163.com");
        map.put("code", "f2e01d");
        System.out.println(userService.login(map));
    }
}
