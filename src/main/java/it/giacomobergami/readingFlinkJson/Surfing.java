package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.utils.csv.writers.CsvSerializer;
import it.giacomobergami.readingFlinkJson.utils.csv.writers.UniformViewCsvWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by vasistas on 09/05/17.
 */
public class Surfing {

  public static void main(String args[]) throws IOException {
    String test = "/Users/vasistas/NestingAndDisjunctionToIndices_slaves=0_parall=1_size=ciccioComplete" +
      ".json";
    File a = new File("/Users/vasistas/1.csv");
    File b = new File("/Users/vasistas/2.csv");
    File c = new File("/Users/vasistas/3.csv");
    CsvSerializer ser = new CsvSerializer(a, b, c);
    ser.serialize(test, new UniformViewCsvWriter(test));
  }

}
