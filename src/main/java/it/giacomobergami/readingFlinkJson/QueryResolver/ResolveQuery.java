package it.giacomobergami.readingFlinkJson.QueryResolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.giacomobergami.readingFlinkJson.ComputationGraph;
import it.giacomobergami.readingFlinkJson.nodes.fields.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * Created by vasistas on 06/05/17.
 */
public class ResolveQuery {

  private String remoteSeriviceHostWithPort;
  private Gson gson;

  public ResolveQuery(String remoteSeriviceHostWithPort) {
    this.remoteSeriviceHostWithPort = remoteSeriviceHostWithPort;
    this.gson = new GsonBuilder().create();
  }

  private String resolveURL(String fullPath) throws IOException {
    URL url = new URL(new URL(remoteSeriviceHostWithPort), fullPath);
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    String strTemp = "";
    StringBuilder sb = new StringBuilder();
    while (null != (strTemp = br.readLine())) {
      sb.append(strTemp);
    }
    return sb.toString();
  }

  public ComputationGraph resolveComputationGraph(String jobId) throws IOException {
    String json = resolveURL("/jobs/"+jobId+"/vertices");
    ComputationGraph toret = ComputationGraph.createComputationGraphFromAjaxQuery(json);
    Vertex[] vertices = toret.getVertices();
    for (int i=0; i<vertices.length; i++) {
      updateVertex(jobId, vertices[i]);
    }
    return toret;
  }

  public void updateVertex(String jobId, Vertex toUpdateWithSubtasks) throws IOException  {
    String json = resolveURL("/jobs/"+jobId+"/vertices/" + toUpdateWithSubtasks.getId());
    Type fileType = new TypeToken<Vertex>(){}.getType();
    Vertex l = gson.fromJson(json, fileType);
    toUpdateWithSubtasks.setSubtasks(l.getSubtasks());

    json = resolveURL("/jobs/"+jobId+"/vertices/" + toUpdateWithSubtasks.getId() + "/subtasktimes");
    l = gson.fromJson(json, fileType);
    toUpdateWithSubtasks.updateSubtaskWithTimestamp(l.getSubtasks());
  }

}
