package com.n1303.birthdaybuddy.service;

import com.n1303.birthdaybuddy.entity.User;
import com.n1303.birthdaybuddy.repository.UserRepository;
import com.n1303.birthdaybuddy.util.search.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.getAllUsers();
  }

  public User upsertUser(String email, String userName, Date dob) {
    return userRepository.updateOrInsertUser(email, userName, dob);
  }

  public List<User> searchUsers(String  query) {
    if (query == null || query.length() == 0) {
      return getAllUsers();
    }

    List<SearchCriteria> searchCriteriaList = Arrays.stream(query.split(",")).map(criteriaString -> {
      String[] criteriaArray = criteriaString.split(" ");
      return new SearchCriteria(
          criteriaArray[0],
          SearchCriteria.SearchOperator.fromValue(criteriaArray[1]),
          criteriaArray[2]);
    }).collect(Collectors.toList());

    return userRepository.getAllUsersThatMatchSearchCriteria(searchCriteriaList);
  }

  public List<User> getAllUsersWithBirthdayOn(int date, int month) {
    return userRepository.getAllUsersWithBirthdayOn(date, month);
  }
}
