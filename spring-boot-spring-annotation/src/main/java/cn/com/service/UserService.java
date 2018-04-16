package cn.com.service;

import cn.com.annotation.MyLog;

public interface UserService {
    @MyLog
    public void addUser();
    public void updateUser();
}
