package it.giacomobergami.readingFlinkJson.utils.csv.writers;

import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.utils.csv.CsvWriter;
import it.giacomobergami.readingFlinkJson.utils.queries.ActualRunningSubtasks;
import it.giacomobergami.readingFlinkJson.utils.queries.ActualRunningTime;
import it.giacomobergami.readingFlinkJson.utils.queries.ActualRunningTimeNoIdling;

/**
 * Created by vasistas on 09/05/17.
 */
public class UniformViewCsvWriter extends CsvWriter<UniformView> {

  private final ActualRunningSubtasks ars;
  private final ActualRunningTime art;
  private final ActualRunningTimeNoIdling artni;

  public UniformViewCsvWriter(String filename) {
    super();
    ars = new ActualRunningSubtasks();
    art = new ActualRunningTime(ars);
    artni =  new ActualRunningTimeNoIdling(ars);
    register("file", x -> filename);
    register("jobId", UniformView::getJid);
    register("duration", UniformView::getDuration);
    register("start", UniformView::getStart_time);
    register("end", UniformView::getEnd_time);
    register("status", UniformView::getState);
    register("numTasks", UniformView::getVertexViewSize);
    register("processStatusCreated", uv -> uv.getStatusCounts().created);
    register("processStatusDeploying", uv -> uv.getStatusCounts().deploying);
    register("processStatusScheduled", uv -> uv.getStatusCounts().scheduled);
    register("processStatusRunning", uv -> uv.getStatusCounts().running);
    register("processStatusFailing", uv -> uv.getStatusCounts().failing);
    register("processStatusFailed", uv -> uv.getStatusCounts().failed);
    register("processStatusCancelling", uv -> uv.getStatusCounts().cancelling);
    register("processStatusCancelled", uv -> uv.getStatusCounts().cancelled);
    register("processStatusRestarting", uv -> uv.getStatusCounts().restarting);
    register("processStatusSuspended", uv -> uv.getStatusCounts().suspended);
    register("timeuvCreated", uv -> uv.getTimestamps().created);
    register("timeuvDeploying", uv -> uv.getTimestamps().deploying);
    register("timeuvScheduled", uv -> uv.getTimestamps().scheduled);
    register("timeuvRunning", uv -> uv.getTimestamps().running);
    register("timeuvFailing", uv -> uv.getTimestamps().failing);
    register("timeuvFailed", uv -> uv.getTimestamps().failed);
    register("timeuvCancelling", uv -> uv.getTimestamps().cancelling);
    register("timeuvCancelled", uv -> uv.getTimestamps().cancelled);
    register("timeuvRestarting", uv -> uv.getTimestamps().restarting);
    register("timeuvSuspended", uv -> uv.getTimestamps().suspended);
    // Some relevant informations retrieved through queries
    register("noSourceSinkDuration", art::apply);
    register("noSourceSink_noIdleDuration", artni::apply);
    register("problemDebug", x -> {
      Long l = art.apply(x);
      if (l > x.getDuration()) {
        System.err.println(filename);
        return true;
      }
      return false;
    });
  }

}
