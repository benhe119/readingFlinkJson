package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.nodes.RawNode;

/**
 * Created by vasistas on 06/05/17.
 */
public class Plan {
  @SerializedName(value = "jid", alternate = {"id"})
  private String jid;

  /**
   * Name associated to the process
   */
  private String name;

  /**
   * Raw information associated to the nodes
   */
  private RawNode[] nodes;

  public RawNode[] getNodes() {
    return nodes;
  }
}
