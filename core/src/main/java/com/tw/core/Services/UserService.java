package com.tw.core.Services;

import com.tw.core.DAO.UserDAO;
import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by taozhang on 12/19/14.
 */
@Service
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
