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
package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Getting the maximum time a non-source/sink task has started
 */
public class EndingRunningTime implements Query<Long> {

  private final ActualRunningSubtasks arst;
  public EndingRunningTime(ActualRunningSubtasks arst) {
    this.arst = arst;
  }

  public EndingRunningTime() {
    this(new ActualRunningSubtasks());
  }

  @Override
  public Long apply(IUniformView iUniformView) {
    return arst.apply(iUniformView)
      .max(Comparator.comparingLong(o -> o.timestamps.finished))
      .map(x -> x.timestamps.finished)
      .orElse(0L);
  }
}
