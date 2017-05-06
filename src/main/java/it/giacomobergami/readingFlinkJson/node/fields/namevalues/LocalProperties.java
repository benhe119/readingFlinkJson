package it.giacomobergami.readingFlinkJson.node.fields.namevalues;


import java.util.List;

/**
 * Created by vasistas on 20/04/17.
 */
public class LocalProperties {
  String order;
  String grouping;
  String uniqueness;

  public LocalProperties(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Order")) {
        order = x.getValue();
      } else if (x.getName().equals("Grouping")) {
        grouping = x.getValue();
      } else if (x.getName().equals("Uniqueness")) {
        uniqueness = x.getValue();
      }
    }
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getGrouping() {
    return grouping;
  }

  public void setGrouping(String grouping) {
    this.grouping = grouping;
  }

  public String getUniqueness() {
    return uniqueness;
  }

  public void setUniqueness(String uniqueness) {
    this.uniqueness = uniqueness;
  }

}
