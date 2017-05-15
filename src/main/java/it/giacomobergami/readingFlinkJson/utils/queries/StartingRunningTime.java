package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Getting the minimum time a non-source/sink task has started
 */
public class StartingRunningTime implements Query<Long> {

  final ActualRunningSubtasks arst;
  public StartingRunningTime(ActualRunningSubtasks arst) {
    this.arst = arst;
  }

  public StartingRunningTime() {
    this(new ActualRunningSubtasks());
  }

  @Override
  public Long apply(IUniformView iUniformView) {
    return arst.apply(iUniformView)
      .min(Comparator.comparingLong(o -> o.timestamps.running))
      .map(x -> x.timestamps.running)
      .orElse(0L);
  }
}
