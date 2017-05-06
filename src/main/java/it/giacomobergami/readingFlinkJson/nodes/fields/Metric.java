package it.giacomobergami.readingFlinkJson.nodes.fields;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vasistas on 06/05/17.
 */
public class Metric {
  @SerializedName("read-bytes")
  private long readedBytes;

  @SerializedName("write-bytes")
  private long writedBytes;

  @SerializedName("read-record")
  private long readedRecords;

  @SerializedName("write-records")
  private long writedRecords;
}
