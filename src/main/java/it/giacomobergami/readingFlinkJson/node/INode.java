package it.giacomobergami.readingFlinkJson.node;

import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.LocalProperties;

/**
 * Created by vasistas on 06/05/17.
 */
public interface INode {
  int getId();
  String getJid();
  String getType();
  String getPact();
  String getContents();
  String getParallelism();
  String getDriverStrategy();
  GlobalProperties getGlobalProperties();
  LocalProperties getLocalProperties();
  Estimates getEstimates();
  Costs getCosts();
  CompilerHints getCompilerHints();
  Predecessor[] getPredecessors();

  default boolean hasPredecessors() {
    return getPredecessors()!=null && getPredecessors().length > 0;
  }

  default Node updateWith(Node node) {
    node.type = getType();
    node.pact = getPact();
    return node;
  }
}
