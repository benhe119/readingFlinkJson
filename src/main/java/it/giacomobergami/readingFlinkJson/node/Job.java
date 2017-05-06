package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Plan;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Created by vasistas on 06/05/17.
 */
public class Job implements IUniformView {
  public String jid;
  public String name;
  public boolean isStoppable;
  public String state;

  @SerializedName("start-time")
  public String start_time;

  @SerializedName("end-time")
  public String end_time;
  public long duration;
  public long now;
  public Timestamp timestamps;

  @SerializedName("status-counts")
  /**
   * Times associated to the run of the whole task. In order to get some
   * finer shaded information, you should visit the other nodes.
   */
  public Timestamp statusCounts;
  private transient Vertex[] vertices;
  private transient Plan plan;

  public RawNode[] getRawNodes() {
    return plan.getNodes();
  }

  public void updateRawNode(int pos, Node n) {
    plan.getNodes()[pos].type = n.type;
    plan.getNodes()[pos].pact = n.pact;
  }

  public NodeVertex getSummarizedView(int pos) {
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
  public String getStart_time() {
    return start_time;
  }

  @Override
  public String getEnd_time() {
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
  public INode getNode(int pos) {
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
}
