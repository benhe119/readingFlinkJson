package it.giacomobergami.readingFlinkJson.nodes;

import it.giacomobergami.readingFlinkJson.Predecessor;
import it.giacomobergami.readingFlinkJson.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.namevalues.LocalProperties;

/**
 * Created by vasistas on 20/04/17.
 */
public class Node {
  public int id;
  public String jid;
  String type;
  String pact;
  String contents;
  String parallelism;
  String driverStrategy;
  GlobalProperties globalProperties;
  LocalProperties localProperties;
  public Estimates estimates;
  public Costs costs;
  CompilerHints compilerHints;
  public Predecessor[] predecessors;

  public Node(RawNode node) {
    this.jid = node.id;
    this.id = Integer.valueOf(node.id);
    this.type = node.type;
    this.pact = node.pact;
    this.contents = node.contents;
    this.parallelism = node.parallelism;
    this.driverStrategy = node.driverStrategy;
    this.globalProperties = new GlobalProperties(node.getGlobalProperties());
    this.localProperties = new LocalProperties(node.getLocalProperties());
    this.estimates = new Estimates(node.getEstimates());
    this.costs = new Costs(node.getCosts());
    this.compilerHints = new CompilerHints(node.getCompilerHints());
    this.predecessors = node.predecessors;
  }

  public Node(int pos, RawNode node) {
    this.id = pos;
    this.jid = node.id;
    this.type = node.type;
    this.pact = node.pact;
    this.contents = node.contents;
    this.parallelism = node.parallelism;
    this.driverStrategy = node.driverStrategy;
    this.globalProperties = new GlobalProperties(node.getGlobalProperties());
    this.localProperties = new LocalProperties(node.getLocalProperties());
    this.estimates = new Estimates(node.getEstimates());
    this.costs = new Costs(node.getCosts());
    this.compilerHints = new CompilerHints(node.getCompilerHints());
    this.predecessors = node.predecessors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Node)) {
      return false;
    }

    Node node = (Node) o;

    return id == node.id;
  }

  @Override
  public int hashCode() {
    return id;
  }
}
