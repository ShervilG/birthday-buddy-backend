package com.n1303.birthdaybuddy.util.search;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class GenericSearchPredicate {

  private List<SearchCriteria> searchCriteriaList;
  private Map<Class<?>, FieldMatchStrategy> fieldMatchStrategyMap;

  @Autowired
  public GenericSearchPredicate(
      List<FieldMatchStrategy> fieldMatchStrategies) {
    this.fieldMatchStrategyMap = new HashMap<>();

    for (FieldMatchStrategy fieldMatchStrategy : fieldMatchStrategies) {
      fieldMatchStrategyMap.put(fieldMatchStrategy.getStrategyClass(), fieldMatchStrategy);
    }
  }

  public boolean test(Object o, List<SearchCriteria> searchCriteriaList) {
    for (SearchCriteria searchCriteria : searchCriteriaList) {
      String fieldName = searchCriteria.getKey();
      String fieldValue = searchCriteria.getValue();

      try {
        Field field = o.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        Object value = field.get(o);

        if (!fieldMatchStrategyMap.get(field.getType()).matchSuccessful(value, fieldValue, searchCriteria.getOperator())) {
          return false;
        }

      } catch (NoSuchFieldException | IllegalAccessException exception) {
        exception.printStackTrace();
      }
    }

    return true;
  }
}
