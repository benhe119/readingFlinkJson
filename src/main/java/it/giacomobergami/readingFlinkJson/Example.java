package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.QueryResolver.ResolveQuery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by vasistas on 06/05/17.
 */
public class Parser {

  public static void main(String args[]) throws Exception {
    // File where the compiler plan is stored
    String compilerPlan = new String(Files.readAllBytes(new File(args[0]).toPath()));
    String flinkLocalHost = args[1];
    String jobId = args[2];

    ComputationGraph cg = ResolveQuery.getComputationGraph(flinkLocalHost, compilerPlan, jobId);

    cg.toJsonString();
  }

}
