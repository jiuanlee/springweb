package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {


    public User selectByPrimaryKey(int userId);

    public List<User> selectAllUser();

    public void insertUser(User user);

    public void deleteUser(int id);

    public List<User> findUsers(String keyWords);

    public void editUser(User user);
}
