package com.tw.web;

import com.tw.core.Exceptions.P2pException;
import com.tw.core.Exceptions.UserNotFoundException;
import com.tw.core.Services.PasswordService;
import com.tw.core.Services.UserService;
import com.tw.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by taozhang on 12/23/14.
 */

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    private UserService userService;
    private PasswordService passwordService;

    @Autowired
    public UsersController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.listAllUser();
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> findOne(@PathVariable("userId") long id) throws UserNotFoundException {
        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            userService.create(user);
            response.setHeader("Location", request.getRequestURL().append("/").append(user.getId()).toString());
        } catch (P2pException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("userId") long id, @RequestBody User user) {
        userService.update(user);
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") long id) {
        userService.deleteUserByID(id);
    }


    @RequestMapping(value = "/:batch", method = RequestMethod.DELETE )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@RequestBody long[] ids) {
        userService.deleteAll(ids);
    }


    @RequestMapping(value = "/:search", method = RequestMethod.GET)
    public List<User> search( @RequestParam(value = "keyword") String keyword) {
        return userService.search(keyword);
    }

}
