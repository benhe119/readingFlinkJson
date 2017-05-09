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

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.node.fields.DataMetrics;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Definition of one node within the task carrying out the computation
 */
public class Vertex {
  public String id;  //
  public String name; //
  public int parallelism; //
  public String status; //

  @SerializedName("start-time")
  public long startTime;

  @SerializedName("end-time")
  public long endTime;

  public long now;

  public long duration;

  public Timestamp tasks;

  public DataMetrics metrics;

  public Subtask[] subtasks;

  public String getId() {
    return id;
  }

  public Subtask[] getSubtasks() {
    return subtasks;
  }

  public void setSubtasks(Subtask[] subtasks) {
    this.subtasks = subtasks;
  }

  public void updateSubtaskWithTimestamp(Subtask[] subtasks) {
    for (int i = 0; i < subtasks.length; i++) {
      this.subtasks[i].setTimestamp(subtasks[i].getTimestamp());
    }
  }
}
