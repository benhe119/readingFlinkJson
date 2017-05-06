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
package it.giacomobergami.readingFlinkJson.node.fields.namevalues;

import java.util.List;

/**
 * Created by vasistas on 20/04/17.
 */
public class GlobalProperties {
  String partitioning;
  String partitioningOrder;
  String uniqueness;

  public GlobalProperties(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Partitioning")) {
        partitioning = x.getValue();
      } else if (x.getName().equals("Partitioning Order")) {
        partitioningOrder = x.getValue();
      } else if (x.getName().equals("Uniqueness")) {
        uniqueness = x.getValue();
      }
    }
  }

  public String getPartitioning() {
    return partitioning;
  }

  public void setPartitioning(String partitioning) {
    this.partitioning = partitioning;
  }

  public String getPartitioningOrder() {
    return partitioningOrder;
  }

  public void setPartitioningOrder(String partitioningOrder) {
    this.partitioningOrder = partitioningOrder;
  }

  public String getUniqueness() {
    return uniqueness;
  }

  public void setUniqueness(String uniqueness) {
    this.uniqueness = uniqueness;
  }
}
