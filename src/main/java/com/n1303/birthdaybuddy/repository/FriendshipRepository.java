package com.n1303.birthdaybuddy.repository;

import com.n1303.birthdaybuddy.entity.Friendship;
import com.n1303.birthdaybuddy.entity.User;
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

    public Friendship insert(String firstUser, String secondUser) {
        Optional<Friendship> existingFriends = friendshipMap.values().stream().filter(friendship -> {
            return Objects.equals(friendship.getFirstUserId() , firstUser) &&
                    Objects.equals(friendship.getSecondUserId() , secondUser) ||
                    Objects.equals(friendship.getFirstUserId() , secondUser) &&
                    Objects.equals(friendship.getSecondUserId() , firstUser);
        }).findAny();

        // If user exists, return it.
        if (existingFriends.isPresent()) {
            return existingFriends.get();
        }

        Friendship friendship = new Friendship().toBuilder()
                .friendshipId(UUID.randomUUID().toString())
                .firstUserId(firstUser)
                .secondUserId(secondUser)
                .build();

        this.friendshipMap.put(friendship.getFriendshipId(), friendship);

        return friendship;
    }

}
