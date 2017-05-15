package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.utils.UtilComparator;
import it.giacomobergami.readingFlinkJson.utils.intervaltree.IntervalTreeFromElement;

/**
 * Returns the actual computation time spent not ideling
 */
public class ActualRunningTimeNoIdling implements Query<Long> {

  private final ActualRunningSubtasks ars;

  public ActualRunningTimeNoIdling() {
    this(new ActualRunningSubtasks());
  }

  public ActualRunningTimeNoIdling(ActualRunningSubtasks ars) {
    this.ars = ars;
  }

  @Override
  public Long apply(IUniformView iUniformView) {
    // Defines an interval tree defined as follows:
    IntervalTreeFromElement<Long, Subtask> tree = new IntervalTreeFromElement<>(
      // 1. The node containing two primitive nodes is no actual existing node
      (x, y) -> null,
      // 2. Use the default Long comparison
      new UtilComparator<Long>() {
      @Override
      public int compare(Long o1, Long o2) {
        return o1.compareTo(o2);
      }
    },
      // 3. The lower bound is the running time
      x -> x.timestamps.running,
      // 4. The upper bound is the finishing time
      x -> x.timestamps.finished);

    // Create a tree using the subtasks for intervals
    ars.apply(iUniformView).forEach(tree::add);

    return tree.getDistanceCovered(0L, (x,y)->x-y, Long::sum);
  }
}
