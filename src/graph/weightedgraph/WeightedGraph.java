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

    public boolean containsNode(String label) {
        return nodes.containsKey(label);
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

    /*
    Prims Algorithm:
        -   start from a node (add it to new tree)
        -   choose the next node via the edge with low cost (weight between nodes) and reach it (add the edge to new tree)
        -   repeat STEP1 & STEP2 without forming a cycle (never visit a node twice)
     */
    public WeightedGraph constructSpanningTree() {
        WeightedGraph spanningTree = new WeightedGraph();
        if (nodes.isEmpty()) {
            return spanningTree;
        }

        // Sort edges with Priority as Weight (since we need low cost tree)
        PriorityQueue<Edge> edges = new PriorityQueue<>(
                Comparator.comparingInt(Edge::getWeight)
        );

        Node currNode = nodes.values().iterator().next();
        edges.addAll(currNode.getEdges());
        spanningTree.addNode(currNode.getLabel());

        if (edges.isEmpty()) {
            return spanningTree;
        }

        while (spanningTree.nodes.size() < nodes.size()) {
            // after adding new edge, since we use PriorityQueue, it gets sorted and we make use of edge with low cost
            var minEdge = edges.remove();
            var nextNode = minEdge.getTo();

            // if a nodes is already reached, skip it (since it can form cycle)
            if (spanningTree.containsNode(nextNode.getLabel())) {
                continue;
            }

            // add the newNode reached via edge with low cost
            spanningTree.addNode(nextNode.getLabel());
            spanningTree.addEdge(minEdge.getFrom().getLabel(), nextNode.getLabel(), minEdge.getWeight());

            // check non-visited nodes and add corresponding edge to resultTree
            for (var edge : nextNode.getEdges()) {
                if (!spanningTree.containsNode(edge.getTo().getLabel())) {
                    edges.add(edge);
                }
            }
        }

        return spanningTree;
    }
}
