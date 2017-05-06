package it.giacomobergami.readingFlinkJson;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.nodes.Node;

import java.util.Map;

/**
 * Created by vasistas on 20/04/17.
 */
public class Predecessor {
  @SerializedName("id")
  String jid;
  String ship_strategy;
  String exchange_mode;
  Node parsedNode;

  public int getId(Map<String, Integer> resolver) {
    return jid.matches("-?\\d+(\\.\\d+)?") ? Integer.valueOf(jid) : resolver.get(jid);
  }
}
