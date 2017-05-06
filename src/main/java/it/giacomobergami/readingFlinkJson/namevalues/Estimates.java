package it.giacomobergami.readingFlinkJson.namevalues;


import java.util.List;
import java.util.Optional;

/**
 * Created by vasistas on 20/04/17.
 */
public class Estimates {
  Optional<Long> outputSize;
  Optional<Long> cardinality;

  public Estimates(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Est. Output Size")) {
        outputSize = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Est. Cardinality")) {
        cardinality = ExtractNumericalValue.extractNumericalValue(x.getValue());
      }
    }
  }

  public Optional<Long> getOutputSize() {
    return outputSize;
  }

  public void setOutputSize(Optional<Long> outputSize) {
    this.outputSize = outputSize;
  }

  public Optional<Long> getCardinality() {
    return cardinality;
  }

  public void setCardinality(Optional<Long> cardinality) {
    this.cardinality = cardinality;
  }
}
