package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Definition of one node within the task carrying out the computation
 */
public class Vertex {
  private String id;
  private String name;
  private int parallelism;
  private String status;

  @SerializedName("start-tume")
  private long startTime;

  @SerializedName("end_time")
  private long endTime;

  private long now;

  private long duration;

  private Timestamp tasks;

  private Metric metrics;

  private Subtask[] subtasks;

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
