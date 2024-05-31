package graph.impl;

public class Main {
    public static void main(String[] args){
        Graph graph = new Graph();
        // Adding Nodes.
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        // Adding Edges
        graph.addEdge("C", "A");
        graph.addEdge("C", "B");
        graph.addEdge("C", "D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("B", "E");
        graph.addEdge("D", "E");
        System.out.println();
        System.out.println("Adjacency List Representation of Graph");
        System.out.println("======================================");
        graph.print();
        System.out.println("======================================\n");

        // Depth First Search Algorithm
        System.out.println("Depth First Search (Recursive)");
        System.out.println("======================================");
        graph.dfsRecursive("C");
        System.out.println("======================================\n");

        System.out.println("Depth First Search (Iterative)");
        System.out.println("======================================");
        graph.dfsIterative("C");
        System.out.println("======================================\n");

        // Breath First Search Algorithm
        System.out.println("Breath First Search (Iterative)");
        System.out.println("======================================");
        graph.bfsIterative("C");
        System.out.println("======================================\n");
    }
}
