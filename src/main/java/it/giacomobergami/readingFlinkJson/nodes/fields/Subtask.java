package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public class Subtask {
  private long subtask;

  private String status;

  private long attempt;

  private String host;

  @SerializedName("start-time")
  private long startTime;

  @SerializedName("end-time")
  private long endTime;

  private long duration;

  private Metric metric;

  private Timestamp timestamps;

  public Timestamp getTimestamp() {
    return timestamps;
  }

  public void setTimestamp(Timestamp timestamps) {
    this.timestamps = timestamps;
  }
}
