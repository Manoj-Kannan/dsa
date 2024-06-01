package graph.weightedgraph;

public class Main {
    public static void main(String[] args) {
        System.out.println("Simple Weighted Graph:::");
        WeightedGraph weightedGraph = new WeightedGraph();
        weightedGraph.addNode("A");
        weightedGraph.addNode("B");
        weightedGraph.addNode("C");

        weightedGraph.addEdge("A", "B", 3);
        weightedGraph.addEdge("A", "C", 2);

        weightedGraph.print();

        // Cycle Detection
        System.out.println("\nCycle Detection:::");
        System.out.println("Graph 1 - " + weightedGraph.hasCycle());
        weightedGraph.addEdge("B", "C", 2);
        System.out.println("Graph 2 - " + weightedGraph.hasCycle());

        // Spanning Tree
        System.out.println("\nSpanning Tree:::");
        WeightedGraph spanningTree = new WeightedGraph();
        spanningTree.addNode("A");
        spanningTree.addNode("B");
        spanningTree.addNode("C");
        spanningTree.addNode("D");
        spanningTree.addEdge("A", "B", 3);
        spanningTree.addEdge("B", "D", 4);
        spanningTree.addEdge("C", "D", 5);
        spanningTree.addEdge("A", "C", 1);
        spanningTree.addEdge("B", "C", 2);

        WeightedGraph resultSpanningTree = spanningTree.constructSpanningTree();
        resultSpanningTree.print();
    }
}
