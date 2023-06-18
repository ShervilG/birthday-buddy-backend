package com.n1303.birthdaybuddy.service.impl;

import com.n1303.birthdaybuddy.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

  @Override
  public String getPingResponse() {
    return "Pong";
  }
}
