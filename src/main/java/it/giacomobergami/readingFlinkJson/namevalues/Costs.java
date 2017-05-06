package it.giacomobergami.readingFlinkJson.namevalues;

import it.giacomobergami.readingFlinkJson.JSONNameValue;

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
