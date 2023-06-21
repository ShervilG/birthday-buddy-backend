package com.n1303.birthdaybuddy.repository;

import com.n1303.birthdaybuddy.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
  private final Map<String, User> userMap;

  public UserRepository() {
    this.userMap = new HashMap<>();
  }

  public User updateOrInsertUser(String email, String userName, Date dateOfBirth) {
    Optional<User> existingUser = userMap.values().stream().filter(user -> {
      return user.getDateOfBirth().getDate() == dateOfBirth.getDate() &&
          user.getDateOfBirth().getMonth() == dateOfBirth.getMonth() &&
          user.getDateOfBirth().getYear() == dateOfBirth.getYear();
    }).findAny();

    // If user exists, return it.
    if (existingUser.isPresent()) {
      return existingUser.get();
    }

    User newUser = new User().toBuilder()
        .userId(UUID.randomUUID().toString())
        .dateOfBirth(dateOfBirth)
        .userEmail(email)
        .userName(userName)
        .build();
    this.userMap.put(newUser.getUserId(), newUser);

    return newUser;
  }
}
