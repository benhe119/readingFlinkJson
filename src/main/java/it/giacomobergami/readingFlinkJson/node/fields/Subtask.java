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
package it.giacomobergami.readingFlinkJson.node.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Defining a subtask within each vertex or node.
 */
public class Subtask {

  public long subtask;

  public String status;

  public long attempt;

  public String host;

  @SerializedName("start-time")
  public long startTime;

  @SerializedName("end-time")
  public long endTime;

  /**
   * Difference between start time and end time
   */
  public long duration;

  public DataMetrics metrics;

  public Timestamp timestamps;

  public Timestamp getTimestamp() {
    return timestamps;
  }

  public void setTimestamp(Timestamp timestamps) {
    this.timestamps = timestamps;
  }
}
