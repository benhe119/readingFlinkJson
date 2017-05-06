package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.fields.Plan;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Created by vasistas on 06/05/17.
 */
public class UniformView implements IUniformView {

  private String jid;
  private String name;
  private boolean isStoppable;
  private String state;

  @SerializedName("start-time")
  private String start_time;

  @SerializedName("end-time")
  private String end_time;
  private long duration;
  private long now;
  private Timestamp timestamps;

  @SerializedName("status-counts")
  /**
   * Times associated to the run of the whole task. In order to get some
   * finer shaded information, you should visit the other nodes.
   */
  private Timestamp statusCounts;
  private NodeVertex[] uniformVertexView;

  public UniformView(Job job) {
    this.jid = job.jid;
    this.name = job.name;
    this.isStoppable = job.isStoppable;
    this.state = job.state;
    this.start_time = job.start_time;
    this.end_time = job.end_time;
    this.duration = job.duration;
    this.now = job.now;
    this.timestamps = job.timestamps;
    this.statusCounts = job.statusCounts;
    this.uniformVertexView = new NodeVertex[job.getVertices().length];
    for (int i=0; i<uniformVertexView.length; i++) {
      uniformVertexView[i] = job.getSummarizedView(i);
    }
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
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
    return uniformVertexView == null || uniformVertexView.length == 0;
  }

  @Override
  public int getVertexViewSize() {
    return isVertexViewEmpty() ? 0 : uniformVertexView.length;
  }

  @Override
  public INode getNode(int pos) {
    return uniformVertexView[pos];
  }

  @Override
  public void updateVertexInPosWithSubtasks(int pos, Subtask[] subtasks) {
    uniformVertexView[pos].subtasks = subtasks;
  }

  @Override
  public void updateVertexInPosWithSubtasksWithTimestamp(int pos, Subtask[] subtasks) {
    for (int i = 0; i < subtasks.length; i++) {
      uniformVertexView[pos].subtasks[i].setTimestamp(subtasks[i].getTimestamp());
    }
  }
}
