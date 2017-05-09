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
