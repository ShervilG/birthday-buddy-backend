package com.n1303.birthdaybuddy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Friendship {
    private String friendshipId;
    private String firstUserId;
    private String secondUserId;
}