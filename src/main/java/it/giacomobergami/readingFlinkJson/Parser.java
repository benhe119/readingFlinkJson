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
    String compilerPlan = new String(Files.readAllBytes(new File
      ("/Users/vasistas/RVFOverSerializedData_slaves=0_parall" +
        "=1_size" +
        "=ciccio.json").toPath()));
    ResolveQuery resolveQuery = new ResolveQuery("http://localhost:8081/");
    ComputationGraph cg =  resolveQuery.getFullPlan(compilerPlan,
      "90a6593d1d7413c38ca8eeda23f6297f");
    System.out.println(cg.toString());

  }

}
