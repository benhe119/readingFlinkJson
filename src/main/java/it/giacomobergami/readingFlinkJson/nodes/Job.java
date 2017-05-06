package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.Utils;
import it.giacomobergami.readingFlinkJson.node.Node;
import it.giacomobergami.readingFlinkJson.node.NodeVertex;
import it.giacomobergami.readingFlinkJson.node.RawNode;
import it.giacomobergami.readingFlinkJson.node.Vertex;
import it.giacomobergami.readingFlinkJson.nodes.fields.Plan;
import it.giacomobergami.readingFlinkJson.nodes.fields.Timestamp;

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

  @SerializedName("status-counts")
  /**
   * Times associated to the run of the whole task. In order to get some
   * finer shaded information, you should visit the other nodes.
   */
  private Timestamp statusCounts;

  @Expose(deserialize = false)
  private Vertex[] vertices;

  private NodeVertex[] uniformVertexView;

  @Expose(deserialize = false)
  private Plan plan;

  public RawNode[] getRawNodes() {
    return plan.getNodes();
  }

  public NodeVertex getSummarizedView(int pos) {
    if ((
      uniformVertexView == null || uniformVertexView.length == 0 || uniformVertexView[pos] == null) &&
      (plan!=null)) {
      Vertex v = Utils.findByProperty(vertices, x -> x.id.equals(plan.getNodes()[pos].id));
      Node n = new Node(pos, plan.getNodes()[pos]);
      return new NodeVertex(v, n);
    } else {
      return uniformVertexView[pos];
    }
  }

  public Vertex[] getVertices() {
    return vertices;
  }

  @Override
  public String toString() {
    if (uniformVertexView == null || (vertices != null && uniformVertexView.length != vertices.length) ||

      uniformVertexView[0] == null) {
      uniformVertexView = new NodeVertex[vertices.length];
    }
    for (int i=0; i<vertices.length; i++) {
      uniformVertexView[i] = getSummarizedView(i);
    }
    vertices = null;
    return new GsonBuilder().setPrettyPrinting().create().toJson(this);
  }
}
