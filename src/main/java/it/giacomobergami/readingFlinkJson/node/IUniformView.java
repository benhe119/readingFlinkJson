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
package it.giacomobergami.readingFlinkJson.node;

import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Created by vasistas on 06/05/17.
 */
public interface IUniformView {
  String getJid();

  String getName();

  boolean isStoppable();

  String getState();

  Long getStart_time();

  Long getEnd_time();

  long getDuration();

  long getNow();

  Timestamp getTimestamps();

  Timestamp getStatusCounts();

  boolean isVertexViewEmpty();

  int getVertexViewSize();

  INode getNode(int pos);

  void updateVertexInPosWithSubtasks(int pos, Subtask[] subtasks);

  void updateVertexInPosWithSubtasksWithTimestamp(int pos, Subtask[] subtasks);
}
