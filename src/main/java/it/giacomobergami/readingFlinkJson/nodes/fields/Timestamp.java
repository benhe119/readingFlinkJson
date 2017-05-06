package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public class Timestamp {
  @SerializedName("CREATED")
  public long created;

  @SerializedName("DEPLOYING")
  public long deploying;

  @SerializedName("SCHEDULED")
  public long scheduled;

  @SerializedName("RUNNING")
  public long running;

  @SerializedName("FAILING")
  public long failing;

  @SerializedName("FAILED")
  public long failed;

  @SerializedName(value = "CANCELLING", alternate = {"CANCELING"})
  public long cancelling;

  @SerializedName("CANCELED")
  public long canceled;

  @SerializedName("FINISHED")
  public long finished;

  @SerializedName("RESTARTING")
  public long restarting;

  @SerializedName("SUSPENDED")
  public long suspended;

  private long getActualRunningTime() {
    return finished - running;
  }
}
