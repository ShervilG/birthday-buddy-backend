package com.n1303.birthdaybuddy.controller;

import com.n1303.birthdaybuddy.common.constant.UrlConstant;
import com.n1303.birthdaybuddy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = UrlConstant.FRONTEND_APP_BASE_URL)
@RequestMapping(UrlConstant.TEST_API_BASE_URL)
public class TestController {
  private final TestService testService;

  @Autowired
  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping(UrlConstant.PING_API_URL)
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>(testService.getPingResponse(), HttpStatus.OK);
  }
}
