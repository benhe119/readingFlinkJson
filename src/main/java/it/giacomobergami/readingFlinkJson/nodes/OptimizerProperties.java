package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.namevalues.JSONNameValue;

import java.util.List;

/**
 * Grouped properties associated to the computation node
 */
public class OptimizerProperties {
  @SerializedName("global_properties")
  List<JSONNameValue> globalProperties;

  @SerializedName("local_properties")
  List<JSONNameValue> localProperties;

  List<JSONNameValue> estimates;
  List<JSONNameValue> costs;

  @SerializedName("compiler_hints")
  List<JSONNameValue> compilerHints;
}
