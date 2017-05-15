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
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.utils.csv.CsvWriter;

/**
 * Created by vasistas on 09/05/17.
 */
public class SubtaskCsvWriter extends CsvWriter<Subtask> {

  public SubtaskCsvWriter(String filename, UniformView Ancestor, Task parent) {
    super();
    register("file", x -> filename);
    register("jobId", x -> Ancestor.getJid());
    register("taskId", x -> Ancestor.getJid());
    register("subtaskId", x -> x.subtask);
    register("host", x -> x.host);
    register("attempt", x -> x.attempt);
    register("status", x -> x.status);
    register("startTime", x -> x.startTime);
    register("endTime", x -> x.endTime);
    register("duration", x -> x.duration);
    register("timestCreated", uv -> uv.timestamps.created);
    register("timestDeploying", uv -> uv.timestamps.deploying);
    register("timestScheduled", uv -> uv.timestamps.scheduled);
    register("timestRunning", uv -> uv.timestamps.running);
    register("timestFailing", uv -> uv.timestamps.failing);
    register("timestFailed", uv -> uv.timestamps.failed);
    register("timestCancelling", uv -> uv.timestamps.cancelling);
    register("timestCancelled", uv -> uv.timestamps.cancelled);
    register("timestRestarting", uv -> uv.timestamps.restarting);
    register("timestSuspended", uv -> uv.timestamps.suspended);
  }

}
