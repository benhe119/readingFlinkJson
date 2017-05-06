package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.nodes.fields.Metric;
import it.giacomobergami.readingFlinkJson.nodes.fields.Subtask;
import it.giacomobergami.readingFlinkJson.nodes.fields.Timestamp;

/**
 * Definition of one node within the task carrying out the computation
 */
public class Vertex {
  public String id;  //
  public String name; //
  public int parallelism; //
  public String status; //

  @SerializedName("start-time")
  public long startTime;

  @SerializedName("end-time")
  public long endTime;

  public long now;

  public long duration;

  public Timestamp tasks;

  public Metric metrics;

  public Subtask[] subtasks;

  public String getId() {
    return id;
  }

  public Subtask[] getSubtasks() {
    return subtasks;
  }

  public void setSubtasks(Subtask[] subtasks) {
    this.subtasks = subtasks;
  }

  public void updateSubtaskWithTimestamp(Subtask[] subtasks) {
    for (int i = 0; i < subtasks.length; i++) {
      this.subtasks[i].setTimestamp(subtasks[i].getTimestamp());
    }
  }
}
