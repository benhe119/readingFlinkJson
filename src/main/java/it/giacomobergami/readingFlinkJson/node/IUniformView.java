package it.giacomobergami.readingFlinkJson.node;

import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
import it.giacomobergami.readingFlinkJson.node.fields.Timestamp;

/**
 * Created by vasistas on 06/05/17.
 */
public interface IUniformView {
  String getJid();

  String getName();

  boolean isStoppable();

  String getState();

  String getStart_time();

  String getEnd_time();

  long getDuration();

  long getNow();

  Timestamp getTimestamps();

  Timestamp getStatusCounts();

  boolean isVertexViewEmpty();

  int getVertexViewSize();

  INode getNode(int pos);

  void updateVertexInPosWithSubtasks(int pos, Subtask[] subtasks);

  void updateVertexInPosWithSubtasksWithTimestamp(int pos, Subtask[] subtasks);
}
