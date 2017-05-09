/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */
package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.LocalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.DataMetrics;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Unifying the Node and Vertex information into one
 */
public class Task implements INode {
  //// IDENTIFICATION
  public int id;
  public String jobId;

  public String getName() {
    return name;
  }

  public String name;

  //// DEFINITION
  public String nodeType;
  public String pact;
  public String contents;

  //// STATUS
  public int parallelism;
  public String status;

  public long getStartTime() {
    return startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public long getDuration() {
    return duration;
  }

  /// STATISTICS
  @SerializedName("start-time")
  public long startTime;
  @SerializedName("end-time")
  public long endTime;
  public long now;
  public long duration;
  public Timestamp tasks;
  public DataMetrics metrics;
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

  public Task(Vertex v, Node n) {
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
