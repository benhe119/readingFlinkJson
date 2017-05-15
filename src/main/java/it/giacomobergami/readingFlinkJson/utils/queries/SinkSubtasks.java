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
import java.util.stream.Stream;

/**
 * Created by vasistas on 15/05/17.
 */
public class SinkSubtasks implements Query<Stream<Subtask>> {
  @Override
  public Stream<Subtask> apply(IUniformView iUniformView) {
    return iUniformView.getNodes()
      .filter(x -> x.getPact().toLowerCase().contains("sink"))
      .flatMap(x -> Arrays.stream(x.subtasks));
  }
}
