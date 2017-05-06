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
 * Metrics that could be obtained for each Node/Vertex or subtask performing a computation
 */
public class Metric {
  @SerializedName("read-bytes")
  public long readedBytes;

  @SerializedName("write-bytes")
  public long writedBytes;

  @SerializedName("read-records")
  public long readedRecords;

  @SerializedName("write-records")
  public long writedRecords;
}
