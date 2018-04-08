package com.nowcoder.service;

import com.nowcoder.dao.UserDao;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUser(int id) {
        return userDao.selectById(id);
    }
}
