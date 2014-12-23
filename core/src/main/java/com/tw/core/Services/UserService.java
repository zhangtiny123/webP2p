package com.tw.core.Services;

import com.tw.core.DAO.UserDAO;
import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by taozhang on 12/19/14.
 */
public class UserService {
    UserDAO userDAO;

    @Autowired
    public UserService (UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> listAllUser() {
        return userDAO.findAll();
    }
}
