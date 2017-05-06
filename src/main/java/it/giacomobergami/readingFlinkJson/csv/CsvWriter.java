package it.giacomobergami.readingFlinkJson.csv;

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

}
