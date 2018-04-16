package cn.com.service.impl;

import cn.com.annotation.MyLog;
import org.springframework.stereotype.Service;
import cn.com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
    // 自定义注解
    @MyLog(value = 110)
    public void addUser() {
        System.out.println("---->>>>>add user");
    }

    public void updateUser() {
        System.out.println("---->>>>>update user");
    }
}
