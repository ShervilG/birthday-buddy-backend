package com.n1303.birthdaybuddy.service;

import com.n1303.birthdaybuddy.entity.Friendship;
import com.n1303.birthdaybuddy.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public List<Friendship> getAll() {
        return friendshipRepository.getAll();
    }

    public void insert(String firstUser, String secondUser) {
        friendshipRepository.insert(firstUser,secondUser);
    }
}
