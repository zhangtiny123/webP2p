package com.tw.core;

import com.tw.core.Exception.P2pException;
import com.tw.core.Services.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring_test.xml")
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
/**
 * Created by taozhang on 12/25/14.
 */
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Transactional
    @Test
    public void should_return_all_the_user_in_database_when_called_listAllUser_function(){
        List<User> userList = userService.listAllUser();

        assertThat(userList.get(1).getName(), is("Jerry"));
        assertThat(userList.get(1).getEmail(), is("jerry@abc.com"));
    }

    @Transactional
    @Test
    public void should_throws_p2p_exception_when_create_user_that_without_email () {
        User user = new User();
        user.setName("testName");
        user.setPassword("testPassword");
        user.setEmail("");

        try {
            userService.create(user);
        } catch (P2pException e) {
            assertThat(e.code, is("100"));
        }
    }

//    @Transactional
//    @Test
//    public void delete_test_user () throws ParseException {
//        User user = new User();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String dateString = "1990-02-03";
//        java.sql.Date birthday = new java.sql.Date(dateFormat.parse(dateString).getTime());
//        user.setName("testName");
//        user.setPassword("testPassword");
//        user.setEmail("testEmail+++++");
//        user.setIdNumber("510000199002034689");
//        user.setRole(Role.INVESTOR);
//        user.setBirthday(birthday);
//
//        User user1 = userService.findByEmail("testEmail+++++");
//        userService.deleteUserWithID(user1.getId());
//    }

    @Transactional
    @Test
    public void should_return_true_when_create_user_successful() throws ParseException {
        User user = new User();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "1990-02-03";
        java.sql.Date birthday = new java.sql.Date(dateFormat.parse(dateString).getTime());
        user.setName("testName");
        user.setPassword("testPassword");
        user.setEmail("testEmail+++++");
        user.setIdNumber("510000199002034689");
        user.setRole(Role.INVESTOR);
        user.setBirthday(birthday);
        List<User> userList = userService.listAllUser();

        userService.create(user);
        List<User> userListAddedItem = userService.listAllUser();

        assertThat(userList.size()+1, is(userListAddedItem.size()));

        User user1 = userService.findByEmail("testEmail+++++");
        userService.deleteUserWithID(user1.getId());
    }
}
