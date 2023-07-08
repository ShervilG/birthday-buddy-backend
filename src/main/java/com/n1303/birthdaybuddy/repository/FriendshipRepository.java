package com.n1303.birthdaybuddy.repository;

import com.n1303.birthdaybuddy.entity.Friendship;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FriendshipRepository {

    private final Map<String, Friendship> friendshipMap;

    public FriendshipRepository() {
        this.friendshipMap = new HashMap<>();
    }

    public List<Friendship> getAll() {
        return new ArrayList<Friendship>(this.friendshipMap.values());
    }

    public void insert(String firstUser, String secondUser) {
        Friendship friendship = new Friendship().toBuilder()
                .friendshipId(UUID.randomUUID().toString())
                .firstUserId(firstUser)
                .secondUserId(secondUser)
                .build();

        this.friendshipMap.put(friendship.getFriendshipId(), friendship);
    }

}
