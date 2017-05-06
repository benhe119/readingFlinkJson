package it.giacomobergami.readingFlinkJson;

/**
 * Created by vasistas on 20/04/17.
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

  public void setName(String name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
