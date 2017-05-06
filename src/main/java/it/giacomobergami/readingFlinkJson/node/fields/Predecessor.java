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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.node.Node;

import java.util.Map;

/**
 * Representing the predecessor of a Node/Vertex
 */
public class Predecessor {

  /**
   * Node associated to the predecessor. It could be either a string or an id
   * dependingly on the data
   */
  @SerializedName("id")
  public String jid;

  /**
   * Defines from which side of the computation the predecessor comes from
   */
  public String side;

  public String ship_strategy;
  public String exchange_mode;

  /**
   * Reference to the parsed element
   */
  @Expose(serialize = false, deserialize = false)
  public Node parsedNode;

  /**
   * Returns the id associated to the predecessor. If the data was analysed from
   * the optimizer with no runtime information, then the id will be itself an id,
   * otherwise it will be a node id that has to be solved.
   *
   * @param resolver  Mapping between the node id representation and the number
   * @return          Node number associated to the element
   */
  public int getId(Map<String, Integer> resolver) {
    return jid.matches("-?\\d+(\\.\\d+)?") ? Integer.valueOf(jid) : resolver.get(jid);
  }
}
