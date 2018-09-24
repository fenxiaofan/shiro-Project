package com.fan.dao;

import com.fan.vo.User;

import java.util.List;

public interface UserDao {
    User getUserByUserName(String userName);

    List<String> getRolesByUserName(String userName);
}
