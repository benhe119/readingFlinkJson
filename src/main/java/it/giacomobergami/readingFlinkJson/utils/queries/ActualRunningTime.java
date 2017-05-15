package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;

/**
 * Created by vasistas on 15/05/17.
 */
public class ActualRunningTime implements Query<Long> {

  ActualRunningSubtasks arst;
  StartingRunningTime srt;
  EndingRunningTime ert;

  public ActualRunningTime() {
    this(new ActualRunningSubtasks());
  }

  public ActualRunningTime(ActualRunningSubtasks arst) {
    this.arst = arst;
    srt = new StartingRunningTime(arst);
    ert = new EndingRunningTime(arst);
  }

  @Override
  public Long apply(IUniformView iUniformView) {
    return ert.apply(iUniformView) - srt.apply(iUniformView);
  }
}
