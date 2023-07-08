package com.n1303.birthdaybuddy.controller;

import com.n1303.birthdaybuddy.common.constant.UrlConstant;
import com.n1303.birthdaybuddy.entity.User;
import com.n1303.birthdaybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UrlConstant.USER_API_BASE_URL)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(UrlConstant.ALL_USER_API_URL)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(UrlConstant.CREATE_USER)
    public ResponseEntity<User> upsertUser(
            @RequestParam("email") String email,
            @RequestParam("userName") String userName,
            @RequestParam("dateOfBirth") Date dob) {
        return new ResponseEntity<>(this.userService.upsertUser(email, userName, dob), HttpStatus.OK);
    }

    @GetMapping(UrlConstant.SEARCH_USER)
    public ResponseEntity<List<User>> searchUsers(@RequestParam("query") String query) {
        return new ResponseEntity<>(this.userService.searchUsers(query), HttpStatus.OK);
    }
}
