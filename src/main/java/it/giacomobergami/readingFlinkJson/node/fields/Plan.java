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
import it.giacomobergami.readingFlinkJson.node.RawNode;

/**
 * Defining a query plan.
 */
public class Plan {
  /**
   * The id could represent either an exadecimal string or an integer,
   * dependingly on the used format
   */
  @SerializedName(value = "jid", alternate = {"id"})
  public String jid;

  /**
   * Name associated to the process
   */
  public String name;

  /**
   * Raw information associated to the nodes
   */
  private RawNode[] nodes;

  public RawNode[] getNodes() {
    return nodes;
  }
}
