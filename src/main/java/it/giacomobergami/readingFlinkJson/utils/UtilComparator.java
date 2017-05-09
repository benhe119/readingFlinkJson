package it.giacomobergami.readingFlinkJson.utils;

import javafx.util.Pair;

import java.util.Comparator;

/**
 * Created by vasistas on 07/05/17.
 */
public abstract class UtilComparator<K> implements Comparator<K> {

  public K min(K left, K right) {
    return compare(left, right) <= 0 ? left : right;
  }

  public K max(K left, K right) {
    return compare(left, right) >= 0 ? left : right;
  }

  public Pair<K, K> sortedPair(K left, K right) {
    return compare(left, right) <= 0 ?
      new Pair<>(left, right) :
      new Pair<>(right, left);
  }

}
