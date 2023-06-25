package com.n1303.birthdaybuddy.service;

import com.n1303.birthdaybuddy.entity.User;
import com.n1303.birthdaybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
}
