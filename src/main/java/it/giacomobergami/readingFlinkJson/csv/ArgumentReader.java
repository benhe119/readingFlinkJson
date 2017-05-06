package it.giacomobergami.readingFlinkJson.csv;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by vasistas on 05/05/17.
 */
public class ArgumentReader<K> {

  private boolean hasFunction;
  private Function<K, ?> function;

  public ArgumentReader() {
    hasFunction = false;
    function = null;
  }

  public ArgumentReader(Function<K, ?> function) {
    this.function = function;
    this.hasFunction = true;
  }

  public boolean hasFunction() {
    return hasFunction;
  }

  public String valueOrElseConstant(K object, Supplier<Object> constant) {
    Object o = (hasFunction ? function.apply(object) : constant.get());
    return o!=null ? "\""+o.toString()+"\"" : "";
  }
}
