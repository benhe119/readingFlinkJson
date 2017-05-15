package it.giacomobergami.readingFlinkJson.utils.csv.writers;

import it.giacomobergami.readingFlinkJson.GsonCommon;
import it.giacomobergami.readingFlinkJson.node.Task;
import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by vasistas on 10/05/17.
 */
public class CsvSerializer {

  private final File viewFile;
  private final File taskFile;
  private final File subTaskFile;
  private boolean firstFirst, secondFirst, thirdFirst;

  /**
   *
   * @param jobFile     File listing all the jobs' informations
   * @param taskFile    File listing all the jobs' tasks
   * @param subTaskFile File listing all the tasks' subtasks
   */
  public CsvSerializer(File jobFile, File taskFile, File subTaskFile) {
    this.viewFile = jobFile;
    this.taskFile = taskFile;
    this.subTaskFile = subTaskFile;
    firstFirst = true;
    secondFirst = true;
    thirdFirst = true;
  }

  public <T extends UniformViewCsvWriter> void serialize(String jsonFile, T uniformViewCsvWriter) throws
    IOException {
    UniformView view = GsonCommon.GSON.fromJson(new FileReader(new File(jsonFile)),
                                                  UniformView.class);
    if (firstFirst) {
      uniformViewCsvWriter.writeHeaderToFile(viewFile);
      firstFirst = false;
    }
    uniformViewCsvWriter.writeToFile(viewFile, view);
    int size = view.getVertexViewSize();
    for (int i = 0; i<size; i++) {
      Task t = view.getNode(i);
      TaskCsvWriter writer = new TaskCsvWriter(jsonFile, view);
      if (secondFirst) {
        writer.writeHeaderToFile(taskFile);
        secondFirst = false;
      }
      writer.writeToFile(taskFile, t);
      int numSubtasks = t.subtasks.length;
      for (int j = 0; j<numSubtasks; j++) {
        Subtask st = t.subtasks[j];
        SubtaskCsvWriter writer2 = new SubtaskCsvWriter(jsonFile, view, t);
        if (thirdFirst) {
          writer2.writeHeaderToFile(subTaskFile);
          thirdFirst = false;
        }
        writer2.writeToFile(subTaskFile, st);
      }
    }
  }

}
