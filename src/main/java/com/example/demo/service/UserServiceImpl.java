package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Override
    public User selectByPrimaryKey(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> selectAllUser() {
        return userDao.selectAllUser();
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);

    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);

    }

    @Override
    public List<User> findUsers(String keyWords) {
        return userDao.findUsers(keyWords);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);

    }
}
