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
package it.giacomobergami.readingFlinkJson.utils.csv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by vasistas on 05/05/17.
 */
public class CsvWriter<K> {

  private final Map<String, ArgumentReader<K>> registering;

  public CsvWriter() {
    registering = new LinkedHashMap<>();
  }

  public CsvWriter<K> register(String column, Function<K, ?> argument) {
    registering.put(column, new ArgumentReader<>(argument));
    return this;
  }

  public CsvWriter<K> register(String column) {
    registering.put(column, new ArgumentReader<>());
    return this;
  }

  public String write(K row, Object... values) {
    StringBuilder sb = new StringBuilder();
    QueueSupplier<Object> elements = new QueueSupplier<>(values);
    Iterator<Map.Entry<String, ArgumentReader<K>>> iterator = registering.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<String, ArgumentReader<K>> x = iterator.next();
      sb.append(x.getValue().valueOrElseConstant(row, elements));
      if (iterator.hasNext()) {
        sb.append(',');
      }
    }
    return sb.toString();
  }

  public void writeHeaderToFile(File f) throws IOException {
    StringBuilder sb = new StringBuilder();
    Iterator<String> it = registering.keySet().iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext()) {
        sb.append(',');
      }
    }
    sb.append('\n');
    Files.write(f.toPath(), (sb.toString()).getBytes(Charset.forName("UTF-8")),
      StandardOpenOption.CREATE, StandardOpenOption.APPEND);
  }

  public void writeToFile(File f, K row, Object... values) throws IOException {
    Files.write(f.toPath(), (write(row, values)+"\n").getBytes(Charset.forName("UTF-8")),
      StandardOpenOption.CREATE, StandardOpenOption.APPEND);
  }

}
