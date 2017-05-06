package it.giacomobergami.readingFlinkJson.nodes;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.JSONNameValue;

import java.util.List;

/**
 * Created by vasistas on 06/05/17.
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
