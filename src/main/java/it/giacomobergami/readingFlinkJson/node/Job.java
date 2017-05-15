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

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Plan;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by vasistas on 06/05/17.
 */
public class Job implements IUniformView {
  public String jid;
  public String name;
  public boolean isStoppable;
  public String state;

  @SerializedName("start-time")
  public long start_time;

  @SerializedName("end-time")
  public long end_time;
  public long duration;
  public long now;
  public Timestamp timestamps;

  @SerializedName("status-counts")
  /**
   * Times associated to the run of the whole task. In order to get some
   * finer shaded information, you should visit the other nodes.
   */
  public Timestamp statusCounts;
  private Vertex[] vertices;
  private Plan plan;

  public RawNode[] getRawNodes() {
    return plan.getNodes();
  }

  public void updateRawNode(int pos, Node n) {
    plan.getNodes()[pos].type = n.type;
    plan.getNodes()[pos].pact = n.pact;
  }

  public Task getSummarizedView(int pos) {
    Vertex v = Utils.findByProperty(vertices, x -> x.id.equals(plan.getNodes()[pos].id));
    Node n = new Node(pos, plan.getNodes()[pos]);
    return new NodeVertexWithNodeUpdate(v, n, this, pos);
  }

  public Vertex[] getVertices() {
    return vertices;
  }

  public UniformView asUniformView() {
    return new UniformView(this);
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(asUniformView());
  }

  @Override
  public String getJid() {
    return jid;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isStoppable() {
    return isStoppable;
  }

  @Override
  public String getState() {
    return state;
  }

  @Override
  public Long getStart_time() {
    return start_time;
  }

  @Override
  public Long getEnd_time() {
    return end_time;
  }

  @Override
  public long getDuration() {
    return duration;
  }

  @Override
  public long getNow() {
    return now;
  }

  @Override
  public Timestamp getTimestamps() {
    return timestamps;
  }

  @Override
  public Timestamp getStatusCounts() {
    return statusCounts;
  }

  @Override
  public boolean isVertexViewEmpty() {
    return vertices == null || vertices.length == 0;
  }

  @Override
  public int getVertexViewSize() {
    return isVertexViewEmpty() ? 0 : vertices.length;
  }

  @Override
  public Task getNode(int pos) {
    return getSummarizedView(pos);
  }

  @Override
  public void updateVertexInPosWithSubtasks(int pos, Subtask[] subtasks) {
    vertices[pos].setSubtasks(subtasks);
  }

  @Override
  public void updateVertexInPosWithSubtasksWithTimestamp(int pos, Subtask[] subtasks) {
    vertices[pos].updateSubtaskWithTimestamp(subtasks);
  }

  @Override
  public Stream<Task> getNodes() {
    return Stream.iterate(0, x -> x+1).limit(getVertexViewSize()).map(this::getNode);
  }
}
