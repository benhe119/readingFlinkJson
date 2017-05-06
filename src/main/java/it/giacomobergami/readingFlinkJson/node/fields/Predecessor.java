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
