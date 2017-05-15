package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by vasistas on 15/05/17.
 */
public class SinkSubtasks implements Query<Stream<Subtask>> {
  @Override
  public Stream<Subtask> apply(IUniformView iUniformView) {
    return iUniformView.getNodes()
      .filter(x -> x.getPact().toLowerCase().contains("sink"))
      .flatMap(x -> Arrays.stream(x.subtasks));
  }
}
