package graph.impl;

import java.util.*;

public class Graph {
    // our structure responsible for keeping track of existing String labels/ nodes in our graph.
    protected Map<String, Node> nodes = new HashMap<>();
    // representation of our graph using a Map, with Nodes as our keys, and Lists of nodes keeping track of our edges.
    protected Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        Node node = new Node(label);

        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        // We're designing a directional graph here, so we'll only add one edge from fromNode to toNode
        // For Undirected graph, we need to add adjacencyList.get(toNode).add(fromNode)
        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        Node removingNode = nodes.get(label);
        if (removingNode == null) {
            return;
        }

        // since we're removing the entire node, we need to remove all the edges pointing towards this node.
        // for each node in out graph (adjacency list) we remove the node from each node. (removing edges to this node)
        for (var n : adjacencyList.keySet()) {
            adjacencyList.get(n).remove(removingNode);
        }
        // removing the nodes from the graph (adjacency list)
        adjacencyList.remove(removingNode);
        nodes.remove(label);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            return;
        }

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            var target = adjacencyList.get(source);
            if (!target.isEmpty()) {
                System.out.println(source + " is connected to " + target);
            }
        }
    }

    public void dfsRecursive(String root) {
        Node node = nodes.get(root);
        if (node == null) {
            return;
        }
        dfsRecursive(node, new HashSet<>());
    }

    private void dfsRecursive(Node root, Set<Node> visited) {
        // Instead of using a set, we can add isVisited param in Node
        System.out.println(root);
        visited.add(root);
        for (var node : adjacencyList.get(root)) {
            if (!visited.contains(node)) {
                dfsRecursive(node, visited);
            }
        }
    }

    // DFS - Stack Impl
    public void dfsIterative(String root) {
        Node node = nodes.get(root);
        if (node == null) {
            return;
        }

        Set<Node> visited = new HashSet<>();

        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            var current = stack.pop();

            if (visited.contains(current)) {
                continue;
            }

            System.out.println(current);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
    }

    // BFS - Queue Impl
    public void bfsIterative(String root) {
        Node node = nodes.get(root);
        if (node == null) {
            return;
        }

        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            var current = queue.remove();

            if (visited.contains(current)) {
                continue;
            }

            System.out.println(current);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
    }
}
