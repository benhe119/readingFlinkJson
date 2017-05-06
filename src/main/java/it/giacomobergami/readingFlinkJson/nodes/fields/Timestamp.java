package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public class Timestamp {
  @SerializedName("CREATED")
  private long created;

  @SerializedName("DEPLOYING")
  private long deploying;

  @SerializedName("SCHEDULED")
  private long scheduled;

  @SerializedName("RUNNING")
  private long running;

  @SerializedName("FAILING")
  private long failing;

  @SerializedName("FAILED")
  private long failed;

  @SerializedName(value = "CANCELLING", alternate = {"CANCELING"})
  private long cancelling;

  @SerializedName("CANCELED")
  private long canceled;

  @SerializedName("FINISHED")
  private long finished;

  @SerializedName("RESTARTING")
  private long restarting;

  @SerializedName("SUSPENDED")
  private long suspended;

  private long getActualRunningTime() {
    return finished - running;
  }
}
