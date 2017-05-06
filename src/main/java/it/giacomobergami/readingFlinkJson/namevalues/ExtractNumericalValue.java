package it.giacomobergami.readingFlinkJson.namevalues;

import java.util.Optional;

/**
 * Created by vasistas on 20/04/17.
 */
public class ExtractNumericalValue {

  public static Optional<Long> extractNumericalValue(String value) {
    if (value.equals("(none)") || value.equals("(unknown)"))
      return Optional.empty();
    String[] num = value.split(" ");
    if (num.length == 1) {
      return Optional.of((long)Double.parseDouble(value));
    } else {
      double mult = 1;
      if (num[1].equals("M")) {
        mult = 1000000L;
      } else if (num[1].equals("K")) {
        mult = 1000L;
      } else if (num[1].equals("G")) {
        mult = 1000000000L;
      } else if (num[1].equals("T")) {
        mult = 1000000000000L;
      }
      return Optional.of((long) (Double.parseDouble(num[0]) * mult));
    }
  }


}
