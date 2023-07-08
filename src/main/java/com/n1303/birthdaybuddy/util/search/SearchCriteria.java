package com.n1303.birthdaybuddy.util.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SearchCriteria {
  private String key;
  private SearchOperator operator;
  private String value;

  @Getter
  @AllArgsConstructor
  public enum SearchOperator {
    EQUAL("="),
    STARTS_WITH("starts"),
    GREATER(">"),
    LESSER("<");

    private String value;

    public static SearchOperator fromValue(String operatorValue) {
      switch (operatorValue) {
        case "=" :
          return EQUAL;
        case ">" :
          return GREATER;
        case "<" :
          return LESSER;
        case "starts":
          return STARTS_WITH;

        default:
          throw new RuntimeException("Illegal operation !");
      }
    }
  }
}
