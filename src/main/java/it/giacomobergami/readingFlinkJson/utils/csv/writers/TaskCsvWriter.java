/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */
package it.giacomobergami.readingFlinkJson.utils.csv.writers;

import it.giacomobergami.readingFlinkJson.node.Task;
import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.utils.csv.CsvWriter;

/**
 * Created by vasistas on 09/05/17.
 */
public class TaskCsvWriter extends CsvWriter<Task> {

  public TaskCsvWriter(String filename, UniformView parent) {
    super();
    register("file", x -> filename);
    register("jobId", x -> parent.getJid());
    register("taskId", Task::getId);
    register("name", Task::getName);
    register("pact", Task::getPact);
    register("type", Task::getType);
    register("isSource", x -> x.getPact().toLowerCase().contains("source"));
    register("isSink", x -> x.getPact().toLowerCase().contains("sink"));
    register("parall", Task::getParallelism);
    register("status", x -> x.status);
    register("startTime", Task::getStartTime);
    register("endTime", Task::getEndTime);
    register("duration", Task::getDuration);
    register("numSubtasks", uv -> uv.subtasks.length);
    register("readedtBytes", x -> x.metrics.readedBytes);
    register("readedtRecords", x -> x.metrics.readedRecords);
    register("writtentBytes", x -> x.metrics.writedBytes);
    register("writtentRecords", x -> x.metrics.writedRecords);
    register("wasCreated", uv -> uv.tasks.created);
    register("wasDeployed", uv -> uv.tasks.deploying);
    register("wasScheduled", uv -> uv.tasks.scheduled);
    register("wasRunning", uv -> uv.tasks.running);
    register("wasFailing", uv -> uv.tasks.failing);
    register("wasFailed", uv -> uv.tasks.failed);
    register("wasCancelling", uv -> uv.tasks.cancelling);
    register("wasCancelled", uv -> uv.tasks.cancelled);
    register("wasRestarting", uv -> uv.tasks.restarting);
    register("wasSuspended", uv -> uv.tasks.suspended);
  }

}
