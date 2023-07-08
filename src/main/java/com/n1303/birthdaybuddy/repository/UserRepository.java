package com.n1303.birthdaybuddy.repository;

import com.n1303.birthdaybuddy.entity.User;
import com.n1303.birthdaybuddy.util.search.GenericSearchPredicate;
import com.n1303.birthdaybuddy.util.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

  @Autowired
  private GenericSearchPredicate searchPredicate;

  private final Map<String, User> userMap;

  public UserRepository() {
    this.userMap = new HashMap<>();
  }

  public User updateOrInsertUser(String email, String userName, Date dateOfBirth) {
    Optional<User> existingUser = userMap.values().stream().filter(user -> {
      return Objects.equals(user.getUserEmail(), email);
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

  public List<User> getAllUsers() {
    return new ArrayList<>(this.userMap.values());
  }


  public List<User> getAllUsersThatMatchSearchCriteria(List<SearchCriteria> searchCriteria) {
    return this.userMap.values().stream().filter(user -> {
      return searchPredicate.test(user, searchCriteria);
    }).collect(Collectors.toList());
  }
}
