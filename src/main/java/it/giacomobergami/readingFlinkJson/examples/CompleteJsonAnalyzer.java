package it.giacomobergami.readingFlinkJson.examples;

import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.utils.csv.writers.CsvSerializer;
import it.giacomobergami.readingFlinkJson.utils.csv.writers.UniformViewCsvWriter;

import java.io.File;
import java.io.IOException;

/**
 * Analizes a list of JSON files produced by RemoteQueryResolvingExample
 */
public class CompleteJsonAnalyzer extends UniformViewCsvWriter {

  public CompleteJsonAnalyzer(String filename) {
    super(filename);
    String[] args = new File(filename).getName()
      .replaceAll(".jsonComplete","")
      .split("_");
    register("algorithm", x -> args[0]);
    register("slaves", x -> args[1].split("=")[1]);
    register("run", x -> args[2].split("=")[1]);
    register("size", x -> args[3].split("=")[1]);
  }

  public static void main(String args[]) throws IOException {

    // Loading the folder containing the .jsonComplete files obtained by RemoteQueryResolvingExample
    File folder = new File(args[0]);
    File[] listOfFiles = folder.listFiles();
    boolean first = true;
    File main = new File(args[1]);
    if (main.exists()) {
      main.delete();
    }
    File middle = new File(args[2]);
    if (middle.exists()) {
      middle.delete();
    }
    File end = new File(args[3]);
    if (end.exists()) {
      end.delete();
    }

    // Serialize the content to csv files
    CsvSerializer serializer = new CsvSerializer(main, middle, end);
    int i = 1;
    for (File f : listOfFiles) {
      System.out.println(f.toString()+ ((i++)+"/"+listOfFiles.length));
      serializer.serialize(f.getAbsolutePath(), new CompleteJsonAnalyzer(f.getAbsolutePath()));
    }
  }

}
