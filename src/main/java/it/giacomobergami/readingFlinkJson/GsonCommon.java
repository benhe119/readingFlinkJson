package it.giacomobergami.readingFlinkJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Created by vasistas on 06/05/17.
 */
public class GsonCommon {

  public static Gson GSON = new GsonBuilder()
    .disableHtmlEscaping()
    .addDeserializationExclusionStrategy(new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        if (fieldAttributes.getName().toLowerCase().equals("father")) {
          System.err.print("");
        }
        Expose e = fieldAttributes.getAnnotation(Expose.class);
        return e != null && !e.deserialize();
      }

      @Override
      public boolean shouldSkipClass(Class<?> aClass) {
        return false;
      }
    })
    .setPrettyPrinting()
    .create();


}
