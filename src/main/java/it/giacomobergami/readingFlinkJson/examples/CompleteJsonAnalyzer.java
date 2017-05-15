/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */
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
