package it.giacomobergami.readingFlinkJson;

import com.google.gson.reflect.TypeToken;
import it.giacomobergami.readingFlinkJson.node.INode;
import it.giacomobergami.readingFlinkJson.node.IUniformView;
import it.giacomobergami.readingFlinkJson.node.Job;
import it.giacomobergami.readingFlinkJson.node.Node;
import it.giacomobergami.readingFlinkJson.node.RawNode;
import it.giacomobergami.readingFlinkJson.node.UniformView;
import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.Subtask;
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
  private HashMap<Integer, INode> nodeHashMap;
  private HashMap<String, Integer> jidConversion;
  private IUniformView originalInformation;

  /**
   * Initializes the whole graph by using the serialized information that incorporates everything
   * @param fromView
   */
  public ComputationGraph(UniformView fromView) {
    this.nodeHashMap = new HashMap<>();
    jidConversion = new HashMap<>();
    for (int i=0; i<fromView.getVertexViewSize(); i++) {
      INode node = fromView.getNode(i);
      nodeHashMap.put(node.getId(), node);
      jidConversion.put(node.getJid(), node.getId());
    }
    originalInformation = fromView;
  }

  /**
   * Low level instantiation
   * @param nodeHashMap           Mapping each node in the computation graph within its position
   * @param jidConversion         Mapping each node id to its position
   * @param originalInformation   Original information for the whole graph
   */
  private ComputationGraph(HashMap<Integer, INode> nodeHashMap,
                           HashMap<String, Integer> jidConversion,
                            IUniformView originalInformation) {
    this.nodeHashMap = nodeHashMap;
    this.jidConversion = jidConversion;
    this.originalInformation = originalInformation;
  }

  @Override
  public String toString() {
    return originalInformation != null ? originalInformation.toString() : "";
  }

  public static ComputationGraph createComputationGraphFromCompilerHint(String json) {
    Type fileType =
      new TypeToken<HashMap<String, ArrayList<RawNode>>>(){}.getType();
    HashMap<Integer, INode> nodeHashMap = new HashMap<>();
    HashMap<String, ArrayList<RawNode>> l = GsonCommon.GSON.fromJson(json, fileType);
    for (ArrayList<RawNode> ls : l.values()) {
      for (RawNode x: ls) {
        nodeHashMap.put(Integer.valueOf(x.id), new Node(x));
      }
    }
    for (Integer key : nodeHashMap.keySet()) {
      INode n = nodeHashMap.get(key);
      if (n.hasPredecessors()) {
        int len = n.getPredecessors().length;
        for (int i=0; i<len; i++) {
          n.getPredecessors()[i].parsedNode = (Node)nodeHashMap.get(n.getPredecessors()[i].jid);
        }
      }
    }
    return new ComputationGraph(nodeHashMap, null, null);
  }

  public ComputationGraph updateComputationGraphFromAjaxQuery(String json) {
    Type fileType = new TypeToken<Job>(){}.getType();
    jidConversion = new HashMap<>();
    originalInformation = GsonCommon.GSON.fromJson(json, fileType);
    int y = 0;
    for (RawNode x: ((Job)originalInformation).getRawNodes()) {
      jidConversion.put(x.id, y);
      Node updater = new Node(y, x);
      nodeHashMap.put(y, nodeHashMap.get(y).updateWith(updater));
      originalInformation.getNode(y).updateWith(updater);
      y++;
    }
    for (Integer key : nodeHashMap.keySet()) {
      INode n = nodeHashMap.get(key);
      if (n.hasPredecessors()) {
        int len = n.getPredecessors().length;
        for (int i=0; i<len; i++) {
          n.getPredecessors()[i].parsedNode = (Node)nodeHashMap.get(n.getPredecessors()[i].jid);
        }
      }
    }
    return this;
  }

  public static ComputationGraph createComputationGraphFromAjaxQuery(String json) {
    Type fileType = new TypeToken<Job>(){}.getType();
    HashMap<Integer, INode> nodeHashMap = new HashMap<>();
    HashMap<String, Integer> jidConversion = new HashMap<>();
    Job l = GsonCommon.GSON.fromJson(json, fileType);
    int y = 0;
    for (RawNode x: l.getRawNodes()) {
      jidConversion.put(x.id, y);
      nodeHashMap.put(y, new Node(y, x));
      y++;
    }
    for (Integer key : nodeHashMap.keySet()) {
      INode n = nodeHashMap.get(key);
      if (n.hasPredecessors()) {
        int len = n.getPredecessors().length;
        for (int i=0; i<len; i++) {
          n.getPredecessors()[i].parsedNode = (Node)nodeHashMap.get(n.getPredecessors()[i].jid);
        }
      }
    }
    return new ComputationGraph(nodeHashMap, jidConversion, l);
  }

  public long exchangedMessagesCardinality() {
    return nodeHashMap
      .values()
      .stream()
      .mapToLong(x -> x.getEstimates().getCardinality().orElseGet(() -> 0L))
      .sum();
  }

  public long exchangedMessagesSize() {
    return nodeHashMap
      .values()
      .stream()
      .mapToLong(x -> x.getEstimates().getOutputSize().orElseGet(() -> 0L))
      .sum();
  }

  public Object getDiskIO() {
    return nodeHashMap.values().stream().mapToLong(x -> x.getCosts().getDiskIO().orElseGet(() -> 0L))
      .sum();
  }

  public Stream<INode> extimateSources() {
    return nodeHashMap.entrySet()
      .stream()
      .filter(x -> x.getValue().hasPredecessors())
      .map(Map.Entry::getValue);
  }

  public String getAdjacencyListAsString() {
    return nodeHashMap.entrySet().stream()
      .filter(x -> x.getValue().hasPredecessors())
      .flatMap(x -> Arrays.stream(x.getValue().getPredecessors()).map(y -> x.getKey()+" "+y.jid))
      .collect(Collectors.joining("\n"));
  }

  public Pair<Integer, Set<Integer>> ingoingElements(Map.Entry<Integer, INode> x) {
    return
      (!x.getValue().hasPredecessors())  ?
        new Pair<>(x.getKey(), new HashSet<>())                                   :

        new Pair<>(x.getKey(), Arrays.stream(x.getValue().getPredecessors()).map(y -> y.getId(jidConversion))
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

  private void updateActualMessageCost(Map<Integer, Set<Integer>> adj,
    List<INode> definedLayer,
    int[] layermap, int currentLayerPos) {

    if (definedLayer.isEmpty())
      return;

    int min = Integer.MAX_VALUE;
    for (int i = 0; i<layermap.length; i++) {
      if (layermap[i] == currentLayerPos && !definedLayer.contains(nodeHashMap.get(i))) {
        definedLayer.add(nodeHashMap.get(i));
      }
    }

    for (INode nl : definedLayer) {
      int max = currentLayerPos;
      if (nl.hasPredecessors()) {
        for (Predecessor p : nl.getPredecessors()) {
          max = Integer.max(max, layermap[p.getId(jidConversion)]+1);
        }
      }
      layermap[nl.getId()] = max;
      min = Integer.min(min, max);
    }

    final Set<INode> currentLayer2 = definedLayer
      .stream()
      .filter(x -> layermap[x.getId()] == currentLayerPos)
      .collect(Collectors.toSet());

    List<INode> nextLevel = adj.entrySet().stream()
      .filter(x -> currentLayer2.contains(nodeHashMap.get(x.getKey())))
      .flatMap(x -> x.getValue().stream().map(nodeHashMap::get))
      .collect(Collectors.toList());

    updateActualMessageCost(adj, nextLevel, layermap, currentLayerPos + 1);
  }

  public double getNodeInformation( HashMap<Integer, ArrayList<INode>> levels,
    Function<INode, Double> function) {
    return levels.entrySet().stream()
      .map(x -> x.getValue().stream().map(function).max(Double::compareTo).orElse
        (0.0))
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
                              x -> x.getEstimates().getOutputSize().orElse(0L).doubleValue() * speedFactor);
  }

  /*
   * Recreates the execution level information from the generated Query Plan
   */
  public HashMap<Integer, ArrayList<INode>> mapLevelToElements() {
    Map<Integer, Set<Integer>> adj = getAdjacencyList();
    Integer max = Collections.max(adj.keySet());
    int[] elementInLayer = new int[max+1];
    Arrays.fill(elementInLayer, -1);
    List<INode> nodes = extimateSources().collect(Collectors.toList());

    updateActualMessageCost(adj, nodes, elementInLayer, 0);

    HashMap<Integer, ArrayList<INode>> toLevels = new HashMap<>();
    for (int i = 0; i<elementInLayer.length; i++) {
      if (!toLevels.containsKey(elementInLayer[i])) {
        toLevels.put(elementInLayer[i], new ArrayList<>());
      }
      toLevels.get(elementInLayer[i]).add(nodeHashMap.get(i));
    }

    return toLevels;
  }

  public String getNodeJid(int pos) {
    return originalInformation.getNode(pos).getJid();
  }

  public void updateVertexInPosWithSubtasks(int pos, Subtask[] subtasks) {
    originalInformation.updateVertexInPosWithSubtasks(pos,subtasks);
  }

  public void updateVertexInPosWithSubtasksWithTimestamp(int pos, Subtask[] subtasks) {
    originalInformation.updateVertexInPosWithSubtasksWithTimestamp(pos,subtasks);
  }

  public int getNumberOfNodes() {
    return originalInformation.getVertexViewSize();
  }
}
