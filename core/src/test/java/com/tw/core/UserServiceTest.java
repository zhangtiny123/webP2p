package com.tw.core;

import com.tw.core.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;
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
    @Rollback
    @Test
    public void should_return_all_the_user_in_database_when_called_listAllUser_function(){
        List<User> userList = userService.listAllUser();

        assertThat(userList.size(), is(6));
        assertThat(userList.get(1).getName(), is("Jerry"));
        assertThat(userList.get(1).getAge(), is(17));
        assertThat(userList.get(1).getEmail(), is("jerry@abc.com"));
    }
}
