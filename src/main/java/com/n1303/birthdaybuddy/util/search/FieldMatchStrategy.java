package com.n1303.birthdaybuddy.util.search;

import com.n1303.birthdaybuddy.util.search.SearchCriteria.SearchOperator;

public interface FieldMatchStrategy {
  boolean matchSuccessful(Object actualValue, String valueToBeMatched, SearchOperator operator);
  Class<?> getStrategyClass();
}
