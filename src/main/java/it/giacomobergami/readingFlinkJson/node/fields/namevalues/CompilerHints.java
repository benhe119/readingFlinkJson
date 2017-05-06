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
package it.giacomobergami.readingFlinkJson.node.fields.namevalues;

import java.util.List;
import java.util.Optional;

/**
 * Created by vasistas on 20/04/17.
 */
public class CompilerHints {
  Optional<Long> outputBytes;
  Optional<Long> outputCardinality;
  Optional<Long> avgRecordSize;
  Optional<Long> filterFactor;

  public CompilerHints(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Output Size (bytes)")) {
        outputBytes = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Output Cardinality")) {
        outputCardinality = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Avg. Output Record Size (bytes)")) {
        avgRecordSize = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Filter Factor")) {
        filterFactor = ExtractNumericalValue.extractNumericalValue(x.getValue());
      }
    }
  }

  public Optional<Long> getOutputBytes() {
    return outputBytes;
  }

  public void setOutputBytes(Optional<Long> outputBytes) {
    this.outputBytes = outputBytes;
  }

  public Optional<Long> getOutputCardinality() {
    return outputCardinality;
  }

  public void setOutputCardinality(Optional<Long> outputCardinality) {
    this.outputCardinality = outputCardinality;
  }

  public Optional<Long> getAvgRecordSize() {
    return avgRecordSize;
  }

  public void setAvgRecordSize(Optional<Long> avgRecordSize) {
    this.avgRecordSize = avgRecordSize;
  }

  public Optional<Long> getFilterFactor() {
    return filterFactor;
  }

  public void setFilterFactor(Optional<Long> filterFactor) {
    this.filterFactor = filterFactor;
  }
}
