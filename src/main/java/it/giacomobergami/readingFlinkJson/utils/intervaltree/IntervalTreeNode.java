package it.giacomobergami.readingFlinkJson.utils.intervaltree;

import it.giacomobergami.readingFlinkJson.GsonCommon;
import it.giacomobergami.readingFlinkJson.utils.UtilComparator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by vasistas on 07/05/17.
 */
public class IntervalTreeNode<K, V> implements Iterable<IntervalTreeNode<K, V>> {

  private K left;
  private K right;
  private V value;
  private IntervalTreeNode<K, V> next;
  private Set<IntervalTreeNode<K, V>> containedIntervals;
  final transient BinaryOperator<V> combineWithNesting;
  final transient UtilComparator<K> comparator;

  /**
   * Iterating over the siblings
   * @return
   */
  @Override
  public SiblingIterator iterator() {
    return new SiblingIterator(this);
  }

  public boolean hasSiblings() {
    return next != null;
  }

  public boolean hasChildren() {
    return containedIntervals.size() > 0;
  }

  /**
   * Iterating over the children
   * @return
   */
  public Iterator<IntervalTreeNode<K, V>> childIterator() {
    return containedIntervals.iterator();
  }

  public class SiblingIterator implements Iterator<IntervalTreeNode<K,V>> {

    IntervalTreeNode<K, V> current;

    public SiblingIterator(IntervalTreeNode<K, V> current) {
      this.current = current;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public IntervalTreeNode<K, V> next() {
      IntervalTreeNode<K, V> toReturn = current;
      current = current.next;
      return toReturn;
    }
  }

  /**
   *
   * @param left                Interval's lower bound
   * @param right               Interval's upper bound
   * @param value               Value associated to the interval
   * @param combineWithNesting  When merging two intervals together, this function combines their
   *                            values into the value to be associated to the final element
   * @param comparator          Comparing the intervals' bounds
   */
  public IntervalTreeNode(K left, K right, V value, BinaryOperator<V> combineWithNesting,
    UtilComparator<K> comparator) {
    this.left = left;
    this.right = right;
    this.value = value;
    this.combineWithNesting = combineWithNesting;
    this.comparator = comparator;
    next = null;
    containedIntervals = new HashSet<>();
  }

  /**
   * Wrapping each object as an interval
   * @param leftElement     Function extracting the lower bound
   * @param rightElement    Function extracting the upper bound
   * @param element         Element where to extract the interval
   * @param <T>             Type of the element
   * @param <K>             Type of the values to be used within the interval
   * @return                @code{element} encapsulated as an interval
   */
  public static <T,K extends Comparable<K>> IntervalTreeNode<K, T> extractFromElement(Function<T,
    K> leftElement, Function<T, K> rightElement, T element) {
    return new IntervalTreeNode<>(
      leftElement.apply(element),
      rightElement.apply(element), element,
      (o, o2) -> null,
      new UtilComparator<K>() {
        @Override
        public int compare(K o1, K o2) {
          return o1.compareTo(o2);
        }
      });
  }

  /**
   * Adding an element inside the interval tree
   * @param element   Element to be added to the tree
   * @return          The updated tree root, corresponding to the smallest interval
   */
  public IntervalTreeNode<K, V> add(IntervalTreeNode<K, V> element) {
    if (element == null || equals(element))
      return this;
    if (comparator.compare(left, element.left) <= 0) {
      return this.putWith(element);
    } else {
      return element.putWith(this);
    }
  }

  /**
   * For the tree with the smallest interval described by the current node, returns the
   * actual space covered by the intervals
   * @param defaultValue              Value to be returned at 0 distance
   * @param distanceFunciton          Length function for the interval. The first argument is
   *                                  the upper bound and the second is the lower bound
   * @param combineDistancesFunction  Combining different distances together
   * @return                          Final distance
   */
  public K getDistanceCovered(K defaultValue, BinaryOperator<K> distanceFunciton, BinaryOperator<K>
    combineDistancesFunction) {
    K distance = defaultValue;
    for (IntervalTreeNode<K, V> next : this) {
      distance = combineDistancesFunction.apply(distance, distanceFunciton.apply(next.right, next.left));
    }
    return distance;
  }

  private IntervalTreeNode<K, V> nullifyNextIn(IntervalTreeNode<K, V> right) {
    right.next = null;
    return this;
  }

  private IntervalTreeNode<K, V> putWith(IntervalTreeNode<K, V> right) {
    if (comparator.compare(this.left, right.left) == 0) {
      return comparator.compare(this.right, right.right) > 0 ?
          this.addChildren(right).add(right.next).nullifyNextIn(right):
          right.addChildren(this).add(this.next).nullifyNextIn(this);
    } else if (comparator.compare(right.left, this.right)>0) {
      if (this.next == null) {
        this.next = right;
      } else {
        this.next = this.next.add(right);
      }
      return this;
    } else {
      if (comparator.compare(right.right, this.right) > 0) {
        V newValue = null;
        if (combineWithNesting != null) {
          newValue = combineWithNesting.apply(this.value, right.value);
        }
        return
          new IntervalTreeNode<>(this.left, right.right, newValue, combineWithNesting, comparator)
            .addChildren(this, right)
            .add(this.next)
            .nullifyNextIn(this)
            .add(right)
            .nullifyNextIn(right);
      } else
        return this.addChildren(right).add(right.next).nullifyNextIn(right);
    }
  }

  private IntervalTreeNode<K, V> addChildren(IntervalTreeNode<K, V>... kvIntervalTreeNode) {
    for (int i = 0; i < kvIntervalTreeNode.length; i++) {
      containedIntervals.add(kvIntervalTreeNode[i]);
    }
    return this;
  }

  @Override
  public String toString() {
    return GsonCommon.GSON.toJson(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IntervalTreeNode)) {
      return false;
    }

    IntervalTreeNode<?, ?> that = (IntervalTreeNode<?, ?>) o;

    if (left != null ? !left.equals(that.left) : that.left != null) {
      return false;
    }
    if (right != null ? !right.equals(that.right) : that.right != null) {
      return false;
    }
    return value != null ? value.equals(that.value) : that.value == null;
  }

  @Override
  public int hashCode() {
    int result = left != null ? left.hashCode() : 0;
    result = 31 * result + (right != null ? right.hashCode() : 0);
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
