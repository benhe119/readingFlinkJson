package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.nodes.fields.Plan;
import it.giacomobergami.readingFlinkJson.nodes.fields.Timestamp;
import it.giacomobergami.readingFlinkJson.nodes.fields.Vertex;

/**
 * Created by vasistas on 06/05/17.
 */
public class Job {
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
  private Vertex[] vertices;

  @SerializedName("status-counts")
  private Timestamp statusCounts;

  private Plan plan;

  public RawNode[] getRawNodes() {
    return plan.getNodes();
  }

  public Vertex[] getVertices() {
    return vertices;
  }

  @Override
  public String toString() {
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
