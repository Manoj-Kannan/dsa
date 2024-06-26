package graph.weightedgraph;

import lombok.Getter;

import java.util.*;

@Getter
public class Node {
    private String label;
    private List<Edge> edges = new ArrayList<>();

    public Node(String label) {
        this.label = label;
    }

    public void addEdge(Node to, int weight) {
        edges.add(new Edge(this, to, weight));
    }

    @Override
    public String toString() {
        return label;
    }
}
