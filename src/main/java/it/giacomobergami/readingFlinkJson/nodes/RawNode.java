package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.JSONNameValue;
import it.giacomobergami.readingFlinkJson.Predecessor;

import java.util.List;

/**
 * Created by vasistas on 20/04/17.
 */
public class RawNode {
  public String id;
  String type;
  String pact;
  String contents;
  String parallelism;
  @SerializedName("driver_strategy")
  String driverStrategy;

  @SerializedName("optimizer_properties")
  private OptimizerProperties optimizerProperties;

  @SerializedName("global_properties")
  private List<JSONNameValue> globalProperties;

  @SerializedName("local_properties")
  private List<JSONNameValue> localProperties;

  private List<JSONNameValue> estimates;
  private List<JSONNameValue> costs;

  @SerializedName("compiler_hints")
  private List<JSONNameValue> compilerHints;

  @SerializedName(value = "predecessors", alternate = {"inputs"})
  Predecessor[] predecessors;

  public List<JSONNameValue> getGlobalProperties() {
    return globalProperties == null ? optimizerProperties.globalProperties : globalProperties;
  }

  public List<JSONNameValue> getLocalProperties() {
    return localProperties == null ? optimizerProperties.localProperties : localProperties;
  }

  public List<JSONNameValue> getEstimates() {
    return estimates == null ? optimizerProperties.estimates : estimates;
  }

  public List<JSONNameValue> getCosts() {
    return costs == null ? optimizerProperties.costs : costs;
  }

  public List<JSONNameValue> getCompilerHints() {
    return compilerHints == null ? optimizerProperties.compilerHints : compilerHints;
  }
}
