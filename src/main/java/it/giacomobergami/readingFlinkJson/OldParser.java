package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.csv.CsvWriter;
import it.giacomobergami.readingFlinkJson.node.Node;

import java.io.File;
import java.io.IOException;

/**
 * Created by vasistas on 20/04/17.
 */
public class OldParser {

  public static void main(String args[]) throws IOException {
    System.out.println("algorithm,workers,size,msg,msgSize,IO");
    File path = new File("/Users/vasistas/Dropbox/Lipsia/Paper/svn/CIKM17_GraphJoins/benchmark/jsons/jsons");
    File[] lists = path.listFiles();
    if (lists != null) {
      for (int i=0; i<lists.length; i++) {
        if (lists[i].getName().endsWith(".json")) {
          CsvWriter<Node> nodeWriter = new CsvWriter<>();
          nodeWriter.register("algorithm");
          nodeWriter.register("workers");
          nodeWriter.register("size");

          String[] parts = lists[i].getName().split("_");
          String algorithm = parts[0];
          Integer slaves = Integer.valueOf(parts[1].replace("slaves=",""));
          Integer size = Integer.valueOf(parts[3].replace("size=","").replace(".json",""));
          //ComputationGraph cg = new ComputationGraph(new File(lists[i]));
          /*cg.mapLevelToElements();
          System.in.read();
          System.out.println(algorithm+","+slaves+","+size+","+cg.exchangedMessagesCardinality()
            +","+cg.exchangedMessagesSize()+","+cg.getDiskIO());*/
        }
      }
    }
  }

}
