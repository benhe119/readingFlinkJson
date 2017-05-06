package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.QueryResolver.ResolveQuery;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by vasistas on 06/05/17.
 */
public class NewParser {

  public static void main(String args[]) throws IOException {
    ResolveQuery resolveQuery = new ResolveQuery("http://localhost:8081/");
    ComputationGraph cg = resolveQuery.resolveComputationGraph("90a6593d1d7413c38ca8eeda23f6297f");
    System.out.println(cg.toString());
    System.out.println(cg.exchangedMessagesCardinality()
      +","+cg.exchangedMessagesSize()+","+cg.getDiskIO());
  }

}
