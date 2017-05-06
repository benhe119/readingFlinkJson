/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */
package it.giacomobergami.readingFlinkJson;

import java.util.function.Predicate;

/**
 * Utility functions
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
