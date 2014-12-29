package com.tw.core.Services;

import com.tw.core.DAO.UserDAO;
import com.tw.core.Exception.P2pException;
import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
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

    public User findOne(long id) {
        return null;
    }
    public User findByPrimaryId(long id) {
        return userDAO.findByPrimaryId(id);
    }

    public void create(User user) throws P2pException {
        verifyUser(user);
        userDAO.createUser(user);
    }

    public void update(User user) {

    }

    public void deleteUserWithID(long id) {
        userDAO.deleteUserWithID(id);
    }

    public void deleteAll(long[] ids) {

    }

    public List<User> search(String keyword) {
        return null;
    }
}
