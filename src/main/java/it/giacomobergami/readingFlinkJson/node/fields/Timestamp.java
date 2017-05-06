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
 * Created by vasistas on 06/05/17.
 */
public class Timestamp {
  @SerializedName("CREATED")
  public long created;

  @SerializedName("DEPLOYING")
  public long deploying;

  @SerializedName("SCHEDULED")
  public long scheduled;

  @SerializedName("RUNNING")
  public long running;

  @SerializedName("FAILING")
  public long failing;

  @SerializedName("FAILED")
  public long failed;

  @SerializedName(value = "CANCELLING", alternate = {"CANCELING"})
  public long cancelling;

  @SerializedName("CANCELED")
  public long canceled;

  @SerializedName("FINISHED")
  public long finished;

  @SerializedName("RESTARTING")
  public long restarting;

  @SerializedName("SUSPENDED")
  public long suspended;

  private long getActualRunningTime() {
    return finished - running;
  }
}
