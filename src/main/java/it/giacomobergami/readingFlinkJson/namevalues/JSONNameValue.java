package it.giacomobergami.readingFlinkJson.namevalues;

/**
 * Apache Flink sometimes could represent the property-values associations in this
 * way. This representation has then to be packed into another representation
 */
public class JSONNameValue {
  String name;
  String value;

  public JSONNameValue(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }
}
