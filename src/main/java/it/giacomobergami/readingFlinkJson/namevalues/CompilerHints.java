package it.giacomobergami.readingFlinkJson.namevalues;

import java.util.List;
import java.util.Optional;

/**
 * Created by vasistas on 20/04/17.
 */
public class CompilerHints {
  Optional<Long> outputBytes;
  Optional<Long> outputCardinality;
  Optional<Long> avgRecordSize;
  Optional<Long> filterFactor;

  public CompilerHints(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Output Size (bytes)")) {
        outputBytes = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Output Cardinality")) {
        outputCardinality = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Avg. Output Record Size (bytes)")) {
        avgRecordSize = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Filter Factor")) {
        filterFactor = ExtractNumericalValue.extractNumericalValue(x.getValue());
      }
    }
  }

  public Optional<Long> getOutputBytes() {
    return outputBytes;
  }

  public void setOutputBytes(Optional<Long> outputBytes) {
    this.outputBytes = outputBytes;
  }

  public Optional<Long> getOutputCardinality() {
    return outputCardinality;
  }

  public void setOutputCardinality(Optional<Long> outputCardinality) {
    this.outputCardinality = outputCardinality;
  }

  public Optional<Long> getAvgRecordSize() {
    return avgRecordSize;
  }

  public void setAvgRecordSize(Optional<Long> avgRecordSize) {
    this.avgRecordSize = avgRecordSize;
  }

  public Optional<Long> getFilterFactor() {
    return filterFactor;
  }

  public void setFilterFactor(Optional<Long> filterFactor) {
    this.filterFactor = filterFactor;
  }
}
