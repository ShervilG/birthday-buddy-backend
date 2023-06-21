package com.n1303.birthdaybuddy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
  private String userId;
  private String userName;
  private String userEmail;
  private Date dateOfBirth;
}
