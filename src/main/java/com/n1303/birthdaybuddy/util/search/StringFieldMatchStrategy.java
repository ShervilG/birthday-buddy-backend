package com.n1303.birthdaybuddy.util.search;

import org.springframework.stereotype.Service;

@Service
public class StringFieldMatchStrategy implements FieldMatchStrategy {

  @Override
  public boolean matchSuccessful(
      Object actualValue,
      String valueToBeMatched,
      SearchCriteria.SearchOperator operator) {
      String actualValueString = (String) actualValue;

      switch (operator) {
        case EQUAL:
          return actualValueString.equalsIgnoreCase(valueToBeMatched);
        case LESSER:
          return actualValueString.compareToIgnoreCase(valueToBeMatched) < 0;
        case GREATER:
          return actualValueString.compareToIgnoreCase(valueToBeMatched) > 0;
        case STARTS_WITH:
          return actualValueString.startsWith(valueToBeMatched);
        default:
          throw new RuntimeException("Illegal operator for String match");
      }
  }

  @Override
  public Class<String> getStrategyClass() {
    return String.class;
  }
}
