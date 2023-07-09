package com.n1303.birthdaybuddy.scheduler;

import com.n1303.birthdaybuddy.entity.User;
import com.n1303.birthdaybuddy.helper.AppScriptHelper;
import com.n1303.birthdaybuddy.service.FriendService;
import com.n1303.birthdaybuddy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InviteCreationScheduler {

  @Autowired
  private AppScriptHelper appScriptHelper;

  @Autowired
  private UserService userService;

  @Autowired
  private FriendService friendService;

  @Scheduled(cron = "0 30 13 * * *", zone = "GMT+5:30")
  public void createBirthdayInvite() {
    log.info("Started Invite Creation scheduler at: {}", new Date());

    // Check service health
    try {
      String response = appScriptHelper.getPingResponse();

      System.out.println(response);
    } catch (Exception e) {
      log.error("Error : {} in  Invite Creation scheduler", e.getMessage());
      return;
    }


    LocalDate tomorrowsDate = LocalDate.now().plusDays(1);
    List<User> users = userService.getAllUsersWithBirthdayOn(tomorrowsDate.getDayOfMonth(),
        tomorrowsDate.getMonthValue());
    if (users == null || users.size() == 0) {
      log.info("Ended Invite Creation scheduler at: {}", new Date());
      return;
    }

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    List<CompletableFuture<Void>> futures = new ArrayList<>();

    users.forEach(user -> {
      futures.add(CompletableFuture.runAsync(() -> {
        List<User> friends = friendService.getAllUsersFriend(user.getUserId());
        if (friends == null || friends.size() == 0) {
          return;
        }

        appScriptHelper.createBirthdayInvite(friends.stream().map(User::getUserEmail).collect(Collectors.toList()));

        log.info("Invite created for: {}'s friends", user.getUserName());
      }));
    });

    CompletableFuture.allOf(futures.toArray(new CompletableFuture[]{})).join();
    executorService.shutdown();


    log.info("Ended Invite Creation scheduler at: {}", new Date());
  }
}
