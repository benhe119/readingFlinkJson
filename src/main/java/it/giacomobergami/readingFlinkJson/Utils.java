package it.giacomobergami.readingFlinkJson;

import java.util.function.Predicate;

/**
 * Created by vasistas on 06/05/17.
 */
public class Utils {

  public static boolean check(boolean condition, String toThrow) {
    if (!condition) {
      throw new RuntimeException(toThrow);
    }
    return condition;
  }

  public static  <K> K checkAssignEqual(K left, K right) {
    if (!left.equals(right)) {
      throw new RuntimeException("Error: " + left.toString() + " != " + right.toString());
    }
    return left;
  }

  public static <K> K findByProperty(K[] array, Predicate<K> fun) {
    if (array == null || array.length == 0) {
      return null;
    } else {
      for (int i=0; i<array.length; i++) {
        if (fun.test(array[i]))
          return array[i];
      }
      return null;
    }
  }

}
