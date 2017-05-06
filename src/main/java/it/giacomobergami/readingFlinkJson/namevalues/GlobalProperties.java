package it.giacomobergami.readingFlinkJson.namevalues;


import it.giacomobergami.readingFlinkJson.JSONNameValue;

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
