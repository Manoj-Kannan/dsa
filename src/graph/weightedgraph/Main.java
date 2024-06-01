package graph.weightedgraph;

public class Main {
    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();
        weightedGraph.addNode("A");
        weightedGraph.addNode("B");
        weightedGraph.addNode("C");

        weightedGraph.addEdge("A", "B", 3);
        weightedGraph.addEdge("A", "C", 2);

        weightedGraph.print();

        // Cycle Detection
        System.out.println("Graph 1 - " + weightedGraph.hasCycle());
        weightedGraph.addEdge("B", "C", 2);
        System.out.println("Graph 2 - " + weightedGraph.hasCycle());
    }
}
