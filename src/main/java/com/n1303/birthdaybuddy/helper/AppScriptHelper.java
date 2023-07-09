package com.n1303.birthdaybuddy.helper;

import com.n1303.birthdaybuddy.model.InviteCreationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class AppScriptHelper {
  @Value("${appscript.baseurl}")
  private String baseUrl;
  private RestTemplate restTemplate;

  public AppScriptHelper() {
    this.restTemplate = new RestTemplate();
  }

  public String getPingResponse() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<String> httpEntity = new HttpEntity<>(headers);

    return restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, String.class).getBody();
  }

  public String createBirthdayInvite(List<String> emailList) {
    InviteCreationRequest inviteCreationRequest = new InviteCreationRequest(emailList);

    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<InviteCreationRequest> httpEntity = new HttpEntity<>(inviteCreationRequest, headers);

    return restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, String.class).getBody();
  }
}
