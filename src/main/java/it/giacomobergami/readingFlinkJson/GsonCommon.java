/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */

package it.giacomobergami.readingFlinkJson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Configurations for the Json file reader and writer
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
