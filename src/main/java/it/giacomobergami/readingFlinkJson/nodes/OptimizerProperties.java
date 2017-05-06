package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.namevalues.JSONNameValue;

import java.util.List;

/**
 * Grouped properties associated to the computation node
 */
public class OptimizerProperties {
  @SerializedName("global_properties")
  public List<JSONNameValue> globalProperties;

  @SerializedName("local_properties")
  public List<JSONNameValue> localProperties;

  public List<JSONNameValue> estimates;
  public List<JSONNameValue> costs;

  @SerializedName("compiler_hints")
  public List<JSONNameValue> compilerHints;
}
