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
package it.giacomobergami.readingFlinkJson.node.fields.namevalues;

import java.util.Optional;

/**
 * Created by vasistas on 20/04/17.
 */
public class ExtractNumericalValue {

  public static Optional<Long> extractNumericalValue(String value) {
    if (value.equals("(none)") || value.equals("(unknown)"))
      return Optional.empty();
    String[] num = value.split(" ");
    if (num.length == 1) {
      return Optional.of((long)Double.parseDouble(value));
    } else {
      double mult = 1;
      if (num[1].equals("M")) {
        mult = 1000000L;
      } else if (num[1].equals("K")) {
        mult = 1000L;
      } else if (num[1].equals("G")) {
        mult = 1000000000L;
      } else if (num[1].equals("T")) {
        mult = 1000000000000L;
      }
      return Optional.of((long) (Double.parseDouble(num[0]) * mult));
    }
  }


}
