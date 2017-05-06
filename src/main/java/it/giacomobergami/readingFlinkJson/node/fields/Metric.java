package it.giacomobergami.readingFlinkJson.node.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Metrics that could be obtained for each Node/Vertex or subtask performing a computation
 */
public class Metric {
  @SerializedName("read-bytes")
  public long readedBytes;

  @SerializedName("write-bytes")
  public long writedBytes;

  @SerializedName("read-records")
  public long readedRecords;

  @SerializedName("write-records")
  public long writedRecords;
}
