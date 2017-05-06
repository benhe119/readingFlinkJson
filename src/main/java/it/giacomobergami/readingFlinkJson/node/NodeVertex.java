package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.LocalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.Metric;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Unifying the Node and Vertex information into one
 */
public class NodeVertex implements INode {
  //// IDENTIFICATION
  public int id;
  public String jobId;
  public String name;

  //// DEFINITION
  public String nodeType;
  public String pact;
  public String contents;

  //// STATUS
  public int parallelism;
  public String status;

  /// STATISTICS
  @SerializedName("start-time")
  public long startTime;
  @SerializedName("end-time")
  public long endTime;
  public long now;
  public long duration;
  public Timestamp tasks;
  public Metric metrics;
  public Subtask[] subtasks;

  //// OPTIMIZER
  public String driverStrategy;
  public GlobalProperties globalProperties;
  public LocalProperties localProperties;
  public Estimates estimates;
  public Costs costs;
  public CompilerHints compilerHints;

  //// COMPUTATION
  public Predecessor[] predecessors;

  public NodeVertex(Vertex v, Node n) {
    this.id = n.id;
    this.jobId = Utils.checkAssignEqual(v.getId(), n.jid);
    this.name = v.name;

    this.nodeType = n.type;
    this.pact = n.pact;
    this.contents = n.contents;

    this.parallelism = Utils.checkAssignEqual(v.parallelism, Integer.valueOf(n.parallelism));
    this.status = v.status;

    this.startTime = v.startTime;
    this.endTime = v.endTime;
    this.now = v.now;
    this.duration = v.duration;
    this.tasks = v.tasks;
    this.metrics = v.metrics;
    this.subtasks = v.subtasks;

    this.driverStrategy = n.driverStrategy;
    this.globalProperties = n.globalProperties;
    this.localProperties = n.localProperties;
    this.estimates = n.estimates;
    this.costs = n.costs;
    this.compilerHints = n.compilerHints;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getJid() {
    return jobId;
  }

  @Override
  public String getType() {
    return nodeType;
  }

  @Override
  public String getPact() {
    return pact;
  }

  @Override
  public String getContents() {
    return contents;
  }

  @Override
  public String getParallelism() {
    return parallelism+"";
  }

  @Override
  public String getDriverStrategy() {
    return driverStrategy;
  }

  @Override
  public GlobalProperties getGlobalProperties() {
    return globalProperties;
  }

  @Override
  public LocalProperties getLocalProperties() {
    return localProperties;
  }

  @Override
  public Estimates getEstimates() {
    return estimates;
  }

  @Override
  public Costs getCosts() {
    return costs;
  }

  @Override
  public CompilerHints getCompilerHints() {
    return compilerHints;
  }

  @Override
  public Predecessor[] getPredecessors() {
    return predecessors;
  }
}
