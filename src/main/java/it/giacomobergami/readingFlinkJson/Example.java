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
package it.giacomobergami.readingFlinkJson;

import it.giacomobergami.readingFlinkJson.get.ResolveQuery;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by vasistas on 06/05/17.
 */
public class Example {

  public static void main(String args[]) throws Exception {
    // Reading the arguments from a file, where each line contains one of the following arguments
    List<String> arguments =
      Files.readAllLines(new File(args[0]).toPath(), Charset.forName("UTF-8"));

    // File where the compiler plan is stored
    String compilerPlan = new String(Files.readAllBytes(new File(arguments.get(0)).toPath()));

    // Process associated to the main flink server
    String flinkLocalHost = arguments.get(1);

    // Id of the processes of which we want to keep track
    String jobId = arguments.get(2);

    // Computation Graph over which we'll evaluate some statistics later on
    ComputationGraph cg = ResolveQuery.getComputationGraph(flinkLocalHost, compilerPlan, jobId);

    // Serializing all the informations as a json file
    File f = new File(arguments.get(0)+"Complete");
    Files.write(f.toPath(), cg.toJsonString().getBytes(Charset.forName("UTF-8")),
      StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
  }

}
