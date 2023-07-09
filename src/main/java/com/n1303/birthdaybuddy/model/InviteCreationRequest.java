package com.n1303.birthdaybuddy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class InviteCreationRequest {

  @JsonProperty("email_list")
  private List<String> emailList;
}
