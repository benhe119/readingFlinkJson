package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public enum NodeType {
  @SerializedName("pact")
  PACT("pact"),

  @SerializedName("source")
  SOURCE("source"),

  @SerializedName("sink")
  SINK("sink");

  public String getNodeType() {
    return nodeType;
  }

  private String nodeType;
  NodeType(String value) {
    nodeType = value;
  }
}
