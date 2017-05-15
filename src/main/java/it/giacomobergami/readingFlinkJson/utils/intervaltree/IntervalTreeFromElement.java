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
package it.giacomobergami.readingFlinkJson.utils.intervaltree;

import it.giacomobergami.readingFlinkJson.utils.UtilComparator;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by vasistas on 07/05/17.
 */
public class IntervalTreeFromElement<K, L> extends IntervalTree<K, L> {

  private final Function<L, K> left, right;

  public IntervalTreeFromElement(BinaryOperator<L> op, UtilComparator<K> comp, Function<L, K> left,
    Function<L, K> right) {
    super(op, comp);
    this.left = left;
    this.right = right;
  }

  public void add(L value) {
    super.add(this.left.apply(value), this.right.apply(value), value);
  }

}
