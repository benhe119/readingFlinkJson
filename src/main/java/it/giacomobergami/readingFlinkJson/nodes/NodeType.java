package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public enum NodeType {
  @SerializedName("pact")
  PACT,

  @SerializedName("source")
  SOURCE,

  @SerializedName("sink")
  SINK;
}
