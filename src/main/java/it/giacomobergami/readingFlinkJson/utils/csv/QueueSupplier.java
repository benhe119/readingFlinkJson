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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

/**
 * Created by vasistas on 05/05/17.
 */
public class QueueSupplier<K> implements Supplier<K> {

  private final Queue<K> mimickSupplier;

  public QueueSupplier(K... elements) {
    mimickSupplier = new LinkedList<>(Arrays.asList(elements));
  }

  @Override
  public K get() {
    return mimickSupplier.isEmpty() ? null : mimickSupplier.remove();
  }
}
