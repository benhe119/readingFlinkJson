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
package it.giacomobergami.readingFlinkJson.node.fields;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.JSONNameValue;

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
