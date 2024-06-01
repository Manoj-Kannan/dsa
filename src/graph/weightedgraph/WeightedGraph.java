package graph.weightedgraph;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

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

    /*
    In AdvancedGraph, we have implemented cycle detection for Directed Cyclic Graphs.
    Here is the implementation for Undirected Cyclic Graphs.
    Involves similar impl with DFS.

    Directed Cyclic Graphs:
        We can keep track of Visiting Nodes
        If the parent is reachable from child or grandChild, its a cycle

        eg: A - B - C - A
        A is not reachable from B
        A is reachable from C
        Forms a cycle

    UnDirected Cyclic Graphs:
        All the parents are reachable from child.
        Since we keep track of from & to nodes, we cant track Visiting Nodes (toNode of a edge contains parentNode (where iteration is started))
        For cycle detection,
            if parent is reached - not a cycle
            other visited node reached - cycle

        eg: A <-> B <-> C <-> A
        A is reachable from B (but not a cycle, since its a parentNode)
        A is reachable from C (a cycle, since C -> B -> A)
     */
    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();

        for (Node currNode : nodes.values()) {
            if (!visited.contains(currNode) && hasCycle(currNode, null, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle(Node currNode, Node parentNode, Set<Node> visited) {
        visited.add(currNode);

        for (Edge edge : currNode.getEdges()) {
            Node toNode = edge.getTo();
            if (toNode == parentNode) {
                continue;
            }

            if (visited.contains(toNode)) {
                return true;
            }

            if (hasCycle(toNode, currNode, visited)) {
                return true;
            }
        }
        return false;
    }
}
