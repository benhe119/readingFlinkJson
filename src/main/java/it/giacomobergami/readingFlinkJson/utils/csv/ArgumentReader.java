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
package it.giacomobergami.readingFlinkJson.utils.csv;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by vasistas on 05/05/17.
 */
public class ArgumentReader<K> {

  private boolean hasFunction;
  private Function<K, ?> function;

  public ArgumentReader() {
    hasFunction = false;
    function = null;
  }

  public ArgumentReader(Function<K, ?> function) {
    this.function = function;
    this.hasFunction = true;
  }

  public boolean hasFunction() {
    return hasFunction;
  }

  public String valueOrElseConstant(K object, Supplier<Object> constant) {
    Object o = (hasFunction ? function.apply(object) : constant.get());
    boolean isNumber = o.toString().matches("-?\\d+(\\.\\d+)?");
    return (isNumber ? "" : "\"") + o.toString() + (isNumber ? "" : "\"");
  }
}
