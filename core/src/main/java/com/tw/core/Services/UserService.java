package com.tw.core.Services;

import com.tw.core.DAO.UserDAO;
import com.tw.core.Exceptions.P2pException;
import com.tw.core.Exceptions.UserNotFoundException;
import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static util.Verify.verifyUser;

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

    public User findOne(long id) throws UserNotFoundException {
        User user = userDAO.findByPrimaryId(id);
        if (user == null) {
            throw new UserNotFoundException("User Not Found!");
        }
        return user;

    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public void create(User user) throws P2pException {
        verifyUser(user);
        userDAO.createUser(user);
    }

    public void update(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUserByID(long id) {
        userDAO.deleteUserByID(id);
    }

    public void deleteAll(long[] ids) {
        for (long id : ids) {
            userDAO.deleteUserByID(id);
        }
    }

    public List<User> search(String keyword) {
        return null;
    }
}
