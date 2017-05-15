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
package it.giacomobergami.readingFlinkJson.utils;

import javafx.util.Pair;

import java.util.Comparator;

/**
 * Created by vasistas on 07/05/17.
 */
public abstract class UtilComparator<K> implements Comparator<K> {

  public K min(K left, K right) {
    return compare(left, right) <= 0 ? left : right;
  }

  public K max(K left, K right) {
    return compare(left, right) >= 0 ? left : right;
  }

  public Pair<K, K> sortedPair(K left, K right) {
    return compare(left, right) <= 0 ?
      new Pair<>(left, right) :
      new Pair<>(right, left);
  }

}
