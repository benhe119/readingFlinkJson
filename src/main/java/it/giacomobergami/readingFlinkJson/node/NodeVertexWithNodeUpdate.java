package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.annotations.Expose;

/**
 * Created by vasistas on 06/05/17.
 */
public class NodeVertexWithNodeUpdate extends NodeVertex {

  private transient Node n;
  private transient Job father;
  private transient int pos;

  public NodeVertexWithNodeUpdate(Vertex v, Node n, Job father, int pos) {
    super(v, n);
    this.n = n;
    this.father = father;
    this.pos = pos;
  }

  @Override
  public Node updateWith(Node node) {
    father.updateRawNode(pos, node);
    return node;
  }
}
