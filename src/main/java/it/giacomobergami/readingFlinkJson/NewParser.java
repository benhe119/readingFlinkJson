package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.QueryResolver.ResolveQuery;

import java.io.IOException;

/**
 * Created by vasistas on 06/05/17.
 */
public class NewParser {

  public static void main(String args[]) throws IOException {
    ResolveQuery resolveQuery = new ResolveQuery("http://localhost:8081/");
    ComputationGraph cg = resolveQuery.resolveRemoveComputationGraph("90a6593d1d7413c38ca8eeda23f6297f");
    System.out.println(cg.toString());
  }

}
