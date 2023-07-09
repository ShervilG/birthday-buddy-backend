package com.n1303.birthdaybuddy.repository;

import com.n1303.birthdaybuddy.entity.Friendship;
import com.n1303.birthdaybuddy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FriendshipRepository {

    @Autowired
    private UserRepository userRepository;

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

        // If friendship exists, return it.
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

    public List<User> getAllFriendsOfUser(String userId) {
        Set<String> friendsUserIdList = friendshipMap.values().stream().filter(friendship ->
            friendship.getFirstUserId().equals(userId) || friendship.getSecondUserId().equals(userId))
            .map(friendship -> friendship.getFirstUserId().equals(userId) ? friendship.getSecondUserId() :
                friendship.getFirstUserId())
            .collect(Collectors.toSet());

        if (friendsUserIdList.size() == 0) {
            return new ArrayList<>();
        }

        return userRepository.getAllUsersInRange(friendsUserIdList);
    }

    public boolean delete(String firstUser, String secondUser) {
        Optional<Friendship> existingFriends = friendshipMap.values().stream().filter(friendship -> {
            return Objects.equals(friendship.getFirstUserId() , firstUser) &&
                    Objects.equals(friendship.getSecondUserId() , secondUser) ||
                    Objects.equals(friendship.getFirstUserId() , secondUser) &&
                            Objects.equals(friendship.getSecondUserId() , firstUser);
        }).findAny();

        if (existingFriends.isPresent()) {
            friendshipMap.remove(existingFriends.get().getFriendshipId());
            return true;
        }

        return false;
    }
}
