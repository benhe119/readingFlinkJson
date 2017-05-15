package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Getting the maximum time a non-source/sink task has started
 */
public class EndingRunningTime implements Query<Long> {

  private final ActualRunningSubtasks arst;
  public EndingRunningTime(ActualRunningSubtasks arst) {
    this.arst = arst;
  }

  public EndingRunningTime() {
    this(new ActualRunningSubtasks());
  }

  @Override
  public Long apply(IUniformView iUniformView) {
    return arst.apply(iUniformView)
      .max(Comparator.comparingLong(o -> o.timestamps.finished))
      .map(x -> x.timestamps.finished)
      .orElse(0L);
  }
}
