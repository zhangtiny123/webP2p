package com.tw.core;

import com.tw.core.Exceptions.P2pException;
import com.tw.core.Exceptions.UserNotFoundException;
import com.tw.core.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        user.setRole("Investor");
        user.setBirthday(birthday);
        List<User> userList = userService.listAllUser();

        userService.create(user);
        List<User> userListAddedItem = userService.listAllUser();

        assertThat(userList.size()+1, is(userListAddedItem.size()));


        User user1 = userService.findByEmail("testEmail+++++");
        userService.deleteUserByID(user1.getId());
    }

    @Transactional
    @Test
    public void should_return_Marry_when_given_id_4_to_findOne_method() throws UserNotFoundException {
        long givenId = 4;

        User foundUser = userService.findOne(givenId);

        assertThat(foundUser.getName(), is("Marry"));
        assertThat(foundUser.getRole(), is(Role.INVESTOR));
    }

    @Transactional
    @Test(expected = UserNotFoundException.class)
    public void should_return_null_when_given_id_100_to_findOne_method() throws UserNotFoundException {
        long givenId = 100;
        User user = userService.findOne(givenId);
    }
}
