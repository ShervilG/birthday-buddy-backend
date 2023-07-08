package com.n1303.birthdaybuddy.controller;

import com.n1303.birthdaybuddy.common.constant.UrlConstant;
import com.n1303.birthdaybuddy.entity.Friendship;
import com.n1303.birthdaybuddy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlConstant.FRIEND_API_BASE_URL)
public class FriendsController {

    @Autowired
    private FriendService friendService;

    @GetMapping(UrlConstant.ALL_USER_API_URL)
    public List<Friendship> getAll() {
        return this.friendService.getAll();
    }

    @PostMapping(UrlConstant.ADD_FRIENDSHIP)
    public ResponseEntity<Friendship> insert(@RequestParam("firstUser") String firstUser,
                                             @RequestParam("secondUser") String secondUser) {
        return new ResponseEntity<>(this.friendService.insert(firstUser,secondUser), HttpStatus.OK);
    }
}
