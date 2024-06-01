package graph.weightedgraph;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {
    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (var node : nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty()) {
                System.out.println(node + " is connected to " + edges);
            }
        }
    }
}
