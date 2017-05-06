package it.giacomobergami.readingFlinkJson.QueryResolver;

import com.google.gson.reflect.TypeToken;
import it.giacomobergami.readingFlinkJson.ComputationGraph;
import it.giacomobergami.readingFlinkJson.GsonCommon;
import it.giacomobergami.readingFlinkJson.node.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * Query resolver for Apache Flink, in order to get runtime information
 */
public class ResolveQuery {

  private String remoteSeriviceHostWithPort;

  public ResolveQuery(String flinkIpWithPort) {
    this.remoteSeriviceHostWithPort = flinkIpWithPort;
  }

  private String getQueryResult(String fullPath) throws IOException {
    URL url = new URL(new URL(remoteSeriviceHostWithPort), fullPath);
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    String strTemp = "";
    StringBuilder sb = new StringBuilder();
    while (null != (strTemp = br.readLine())) {
      sb.append(strTemp);
    }
    return sb.toString();
  }

  public ComputationGraph getFullPlan(String jsonQueryPlan, String jobId) throws IOException {
    ComputationGraph toret =  ComputationGraph
      .createComputationGraphFromCompilerHint(jsonQueryPlan)
      .updateComputationGraphFromAjaxQuery(getQueryResult("/jobs/"+jobId+"/vertices"));
    int len = toret.getNumberOfNodes();
    for (int i=0; i<len; i++) {
      updateVertexInformationWithFurtherQueries(jobId, toret, i);
    }
    return toret;
  }

  public static ComputationGraph getComputationGraph(String flinkIpWithPort, String
    jsonCompilerQueryPlan, String jobId) throws IOException {
    return new ResolveQuery(flinkIpWithPort).getFullPlan(jsonCompilerQueryPlan, jobId);
  }

  private void updateVertexInformationWithFurtherQueries(String jobId, ComputationGraph toret, int
    pos)
    throws IOException  {

    String nodeJid = toret.getNodeJid(pos);
    String json = getQueryResult("/jobs/"+jobId+"/vertices/" + nodeJid);
    Type fileType = new TypeToken<Vertex>(){}.getType();
    Vertex l = GsonCommon.GSON.fromJson(json, fileType);
    toret.updateVertexInPosWithSubtasks(pos, l.getSubtasks());

    json = getQueryResult("/jobs/"+jobId+"/vertices/" + nodeJid + "/subtasktimes");
    l = GsonCommon.GSON.fromJson(json, fileType);
    toret.updateVertexInPosWithSubtasksWithTimestamp(pos, l.getSubtasks());
  }

}
