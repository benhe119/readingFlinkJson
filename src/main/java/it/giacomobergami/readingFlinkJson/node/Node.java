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

import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.LocalProperties;

/**
 * Created by vasistas on 20/04/17.
 */
public class Node implements INode{
  public int id; //
  public String jid; //
  String type; //
  String pact; //
  String contents; //
  String parallelism; //
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

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getJid() {
    return jid;
  }

  @Override
  public String getType() {
    return type;
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
    return parallelism;
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
