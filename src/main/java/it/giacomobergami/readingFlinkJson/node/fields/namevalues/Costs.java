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
public class Costs {
  Optional<Long> network;
  Optional<Long> diskIO;
  Optional<Long> CPU;
  Optional<Long> cumulativeNetwork;
  Optional<Long> cumulativeDiskIO;
  Optional<Long> cumulativeCPU;

  public Costs(List<JSONNameValue> name) {
    for (JSONNameValue x : name) {
      if (x.getName().equals("Network")) {
        network = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Disk I/O")) {
        diskIO = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("CPU")) {
        CPU = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Cumulative Network")) {
        cumulativeNetwork = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Cumulative Disk I/O")) {
        cumulativeDiskIO = ExtractNumericalValue.extractNumericalValue(x.getValue());
      } else if (x.getName().equals("Cumulative CPU")) {
        cumulativeCPU = ExtractNumericalValue.extractNumericalValue(x.getValue());
      }
    }
  }

  public Optional<Long> getNetwork() {
    return network;
  }

  public void setNetwork(Optional<Long> network) {
    this.network = network;
  }

  public Optional<Long> getDiskIO() {
    return diskIO;
  }

  public void setDiskIO(Optional<Long> diskIO) {
    this.diskIO = diskIO;
  }

  public Optional<Long> getCPU() {
    return CPU;
  }

  public void setCPU(Optional<Long> CPU) {
    this.CPU = CPU;
  }

  public Optional<Long> getCumulativeNetwork() {
    return cumulativeNetwork;
  }

  public void setCumulativeNetwork(Optional<Long> cumulativeNetwork) {
    this.cumulativeNetwork = cumulativeNetwork;
  }

  public Optional<Long> getCumulativeDiskIO() {
    return cumulativeDiskIO;
  }

  public void setCumulativeDiskIO(Optional<Long> cumulativeDiskIO) {
    this.cumulativeDiskIO = cumulativeDiskIO;
  }

  public Optional<Long> getCumulativeCPU() {
    return cumulativeCPU;
  }

  public void setCumulativeCPU(Optional<Long> cumulativeCPU) {
    this.cumulativeCPU = cumulativeCPU;
  }
}
