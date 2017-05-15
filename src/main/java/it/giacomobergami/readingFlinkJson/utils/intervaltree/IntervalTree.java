package it.giacomobergami.readingFlinkJson.utils.intervaltree;

import it.giacomobergami.readingFlinkJson.utils.UtilComparator;

import java.util.function.BinaryOperator;

/**
 * Created by vasistas on 07/05/17.
 */
public class IntervalTree<K, L> {

  private final BinaryOperator<L> op;
  private final UtilComparator<K> comp;
  private IntervalTreeNode<K, L> tree;

  public IntervalTree(BinaryOperator<L> op, UtilComparator<K> comp) {
    this.op = op;
    this.comp = comp;
    tree = null;
  }

  public void add(K left, K right, L value) {
    if (tree == null) {
      tree = new IntervalTreeNode<>(left, right, value, op, comp);
    } else {
      tree = tree.add(new IntervalTreeNode<>(left, right, value, op, comp));
    }
  }

  public K getDistanceCovered(K orElse, BinaryOperator<K> distanceFunciton, BinaryOperator<K> combineDistancesFunction) {
    return tree == null ? orElse :
      tree.getDistanceCovered(orElse, distanceFunciton, combineDistancesFunction);
  }

  public static void main(String args[]) {
    BinaryOperator<Long> lop = (aLong, aLong2) -> aLong + aLong2;
    UtilComparator<Integer> lcomp = new UtilComparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
      }
    };
    IntervalTree<Integer, Long> it = new IntervalTree<>(lop, lcomp);
    it.add(0, 5, 10L);
    it.add(10, 15, 20L);
    it.add(20, 25, 30L);
    it.add(6, 8, 2L);
    it.add(1, 2, 0L);
    it.add(10, 16, 1L);
    it.add(2, 7, 3L);
    it.add(5, 10, 0L);
    it.add(6, 19, 0L);
    //System.out.println(it.tree);
    System.out.println(it.getDistanceCovered(0, (x, y)-> x-y, Integer::sum));

  }

}
