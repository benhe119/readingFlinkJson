package it.giacomobergami.readingFlinkJson.utils.csv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

/**
 * Created by vasistas on 05/05/17.
 */
public class QueueSupplier<K> implements Supplier<K> {

  private final Queue<K> mimickSupplier;

  public QueueSupplier(K... elements) {
    mimickSupplier = new LinkedList<>(Arrays.asList(elements));
  }

  @Override
  public K get() {
    return mimickSupplier.isEmpty() ? null : mimickSupplier.remove();
  }
}
