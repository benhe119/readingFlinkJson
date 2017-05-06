package it.giacomobergami.readingFlinkJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.giacomobergami.readingFlinkJson.nodes.Node;
import it.giacomobergami.readingFlinkJson.nodes.RawNode;
import it.giacomobergami.readingFlinkJson.nodes.Job;
import it.giacomobergami.readingFlinkJson.nodes.fields.Vertex;
import javafx.util.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vasistas on 20/04/17.
 */
public class ComputationGraph {

  /**
   * Mapping each node position to the actual node information
   */
  private HashMap<Integer, Node> nodeHashMap;
  private HashMap<String, Integer> jidConversion;
  private Job originalInformation;

  private ComputationGraph(HashMap<Integer, Node> nodeHashMap,
                           HashMap<String, Integer> jidConversion,
                           Job originalInformation) {
    this.nodeHashMap = nodeHashMap;
    this.jidConversion = jidConversion;
    this.originalInformation = originalInformation;
  }

  @Override
  public String toString() {
    return originalInformation != null ? originalInformation.toString() : "";
  }

  public static ComputationGraph createComputationGraphFromCompilerHint(String json) {
    Gson gson = new Gson();
    Type fileType =
      new TypeToken<HashMap<String, ArrayList<RawNode>>>(){}.getType();
    HashMap<Integer, Node> nodeHashMap = new HashMap<>();
    HashMap<String, ArrayList<RawNode>> l = gson.fromJson(json, fileType);
    for (ArrayList<RawNode> ls : l.values()) {
      for (RawNode x: ls) {
        nodeHashMap.put(Integer.valueOf(x.id), new Node(x));
      }
    }
    for (Integer key : nodeHashMap.keySet()) {
      Node n = nodeHashMap.get(key);
      if (n.predecessors != null && n.predecessors.length > 0) {
        for (int i=0; i<n.predecessors.length; i++) {
          n.predecessors[i].parsedNode = nodeHashMap.get(n.predecessors[i].jid);
        }
      }
    }
    return new ComputationGraph(nodeHashMap, null, null);
  }

  public static ComputationGraph createComputationGraphFromAjaxQuery(String json) {
    Gson gson = new Gson();
    Type fileType = new TypeToken<Job>(){}.getType();
    HashMap<Integer, Node> nodeHashMap = new HashMap<>();
    HashMap<String, Integer> jidConversion = new HashMap<>();
    Job l = gson.fromJson(json, fileType);
    int y = 0;
    for (RawNode x: l.getRawNodes()) {
      jidConversion.put(x.id, y);
      nodeHashMap.put(y, new Node(y, x));
      y++;
    }
    for (Integer key : nodeHashMap.keySet()) {
      Node n = nodeHashMap.get(key);
      if (n.predecessors != null && n.predecessors.length > 0) {
        for (int i=0; i<n.predecessors.length; i++) {
          n.predecessors[i].parsedNode = nodeHashMap.get(n.predecessors[i].jid);
        }
      }
    }
    return new ComputationGraph(nodeHashMap, jidConversion, l);
  }

  public long exchangedMessagesCardinality() {
    return nodeHashMap
      .values()
      .stream()
      .mapToLong(x -> x.estimates.getCardinality().orElseGet(() -> 0L))
      .sum();
  }

  public long exchangedMessagesSize() {
    return nodeHashMap
      .values()
      .stream()
      .mapToLong(x -> x.estimates.getOutputSize().orElseGet(() -> 0L))
      .sum();
  }

  public Object getDiskIO() {
    return nodeHashMap.values().stream().mapToLong(x -> x.costs.getDiskIO().orElseGet(() -> 0L)).sum();
  }

  public Stream<Node> extimateSources() {
    return nodeHashMap.entrySet()
      .stream()
      .filter(x -> x.getValue().predecessors==null || x.getValue().predecessors.length == 0)
      .map(Map.Entry::getValue);
  }

  public String getAdjacencyListAsString() {
    return nodeHashMap.entrySet().stream()
      .filter(x -> x.getValue().predecessors != null && x.getValue().predecessors.length > 0)
      .flatMap(x -> Arrays.stream(x.getValue().predecessors).map(y -> x.getKey()+" "+y.jid))
      .collect(Collectors.joining("\n"));
  }

  public Pair<Integer, Set<Integer>> ingoingElements(Map.Entry<Integer, Node> x) {
    return
      x.getValue().predecessors == null || x.getValue().predecessors.length == 0  ?
        new Pair<>(x.getKey(), new HashSet<>())                                   :

        new Pair<>(x.getKey(), Arrays.stream(x.getValue().predecessors).map(y -> y.getId(jidConversion))
          .collect
          (Collectors.toSet()));
  }

  public static HashMap<Integer, Set<Integer>> updateWithElement(HashMap<Integer, Set<Integer>> m,
    Integer qKey, Set<Integer> qValue) {
    if (m.containsKey(qKey)) {
      m.get(qKey).addAll(qValue);
    } else {
      m.put(qKey, qValue);
    }
    return m;
  }

  public Map<Integer, Set<Integer>> getAdjacencyList() {
    return nodeHashMap.entrySet().stream()
      .map(this::ingoingElements)
      .map(x -> { System.out.println("D" + x); return x; })
      .flatMap(s -> {
        HashSet<Pair<Integer, Set<Integer>>> toRetStreamed = new HashSet<>();
        toRetStreamed.add(new Pair<>(s.getKey(), new HashSet<>()));
        for (Integer y : s.getValue()) {
          Set<Integer> singleton = new HashSet<>(1);
          singleton.add(s.getKey());
          toRetStreamed.add(new Pair<>(y, singleton));
        }
        return toRetStreamed.stream();
      })
      .collect(Collectors.groupingBy(Pair::getKey, new Collector<Pair<Integer,Set<Integer>>,
        Set<Integer>, Set<Integer>>() {
        @Override
        public Supplier<Set<Integer>> supplier() {
          return HashSet::new;
        }

        @Override
        public BiConsumer<Set<Integer>, Pair<Integer, Set<Integer>>> accumulator() {
          return (integers, integerSetPair) -> integers.addAll(integerSetPair.getValue());
        }

        @Override
        public BinaryOperator<Set<Integer>> combiner() {
          return (integers, integers2) -> {
            integers.addAll(integers2);
            return integers;
          };
        }

        @Override
        public Function<Set<Integer>, Set<Integer>> finisher() {
          return x -> x;
        }

        @Override
        public Set<Characteristics> characteristics() {
          return new HashSet<>();
        }
      }));
  }

  private void updateActualMessageCost(Map<Integer, Set<Integer>> adj, List<Node> definedLayer,
    int[] layermap, int currentLayerPos) {

    if (definedLayer.isEmpty())
      return;

    int min = Integer.MAX_VALUE;
    for (int i = 0; i<layermap.length; i++) {
      if (layermap[i] == currentLayerPos && !definedLayer.contains(nodeHashMap.get(i))) {
        definedLayer.add(nodeHashMap.get(i));
      }
    }

    for (Node nl : definedLayer) {
      int max = currentLayerPos;
      if (nl.predecessors != null) {
        for (Predecessor p : nl.predecessors) {
          max = Integer.max(max, layermap[p.getId(jidConversion)]+1);
        }
      }
      layermap[nl.id] = max;
      min = Integer.min(min, max);
    }

    final Set<Node> currentLayer2 = definedLayer
      .stream()
      .filter(x -> layermap[x.id] == currentLayerPos)
      .collect(Collectors.toSet());

    List<Node> nextLevel = adj.entrySet().stream()
      .filter(x -> currentLayer2.contains(nodeHashMap.get(x.getKey())))
      .flatMap(x -> x.getValue().stream().map(nodeHashMap::get))
      .collect(Collectors.toList());

    updateActualMessageCost(adj, nextLevel, layermap, currentLayerPos + 1);
  }

  public double getNodeInformation( HashMap<Integer, ArrayList<Node>> levels, Function<Node, Double> function) {
    return levels.entrySet().stream()
      .map(x -> x.getValue().stream().map(function).max(Double::compareTo).orElse(0.0))
      .max(Double::compareTo)
      .orElse(0.0);
  }

  public  double getRatio(int x, Map<Integer, Set<Integer>> adjList, int nextLevel, HashMap<Integer,
    ArrayList<Node>> levels) {
    Set<Integer> out = new HashSet<>(adjList.get(x));
    double max = out.size();
    if (max == 0.0) return  0.0;
    out.removeAll(levels.get(nextLevel).stream().map(n -> n.id).collect(Collectors.toList()));
    return ((double)out.size()) / max;
  }

  public double getNodeInformationLevelwise(HashMap<Integer, ArrayList<Node>> levels,
    Function<Node, Double> function) {
    return levels.entrySet().stream()
      .map(x -> x.getValue().stream().map(y ->
        function.apply(y) * getRatio(y.id, getAdjacencyList(), x.getKey()+1, levels)).max(Double::compareTo).orElse(0.0)
      )
      .max(Double::compareTo)
      .orElse(0.0);
  }

  public double getMessageTime(double speedFactor) {
    return getNodeInformation(mapLevelToElements(),
                              x -> x.estimates.getOutputSize().orElse(0L).doubleValue() * speedFactor);
  }

  /*
   * Recreates the execution level information from the generated Query Plan
   */
  public HashMap<Integer, ArrayList<Node>> mapLevelToElements() {
    Map<Integer, Set<Integer>> adj = getAdjacencyList();
    Integer max = Collections.max(adj.keySet());
    int[] elementInLayer = new int[max+1];
    Arrays.fill(elementInLayer, -1);
    List<Node> nodes = extimateSources().collect(Collectors.toList());

    updateActualMessageCost(adj, nodes, elementInLayer, 0);

    HashMap<Integer, ArrayList<Node>> toLevels = new HashMap<>();
    for (int i = 0; i<elementInLayer.length; i++) {
      if (!toLevels.containsKey(elementInLayer[i])) {
        toLevels.put(elementInLayer[i], new ArrayList<>());
      }
      toLevels.get(elementInLayer[i]).add(nodeHashMap.get(i));
    }

    return toLevels;
  }

  public Vertex[] getVertices() {
    return originalInformation.getVertices();
  }
}
