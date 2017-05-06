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
