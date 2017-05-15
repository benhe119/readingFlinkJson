package it.giacomobergami.readingFlinkJson.utils.intervaltree;

import it.giacomobergami.readingFlinkJson.utils.UtilComparator;

import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by vasistas on 07/05/17.
 */
public class IntervalTreeFromElement<K, L> extends IntervalTree<K, L> {

  private final Function<L, K> left, right;

  public IntervalTreeFromElement(BinaryOperator<L> op, UtilComparator<K> comp, Function<L, K> left,
    Function<L, K> right) {
    super(op, comp);
    this.left = left;
    this.right = right;
  }

  public void add(L value) {
    super.add(this.left.apply(value), this.right.apply(value), value);
  }

}
