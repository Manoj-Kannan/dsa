package graph.dfs;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Order of Child is not maintained
        new DepthFirstSearch<Integer>().traverse(getSoueceVertex());
        System.out.println();
        // Order of Child is maintained
        new DepthFirstSearch<Integer>().traverseRecursively(getSoueceVertex());
    }

    private static Vertex<Integer> getSoueceVertex() {
        Vertex<Integer> v0 = new Vertex<>(0);
        Vertex<Integer> v1 = new Vertex<>(1);
        Vertex<Integer> v2 = new Vertex<>(2);
        Vertex<Integer> v3 = new Vertex<>(3);
        Vertex<Integer> v4 = new Vertex<>(4);
        Vertex<Integer> v5 = new Vertex<>(5);
        Vertex<Integer> v6 = new Vertex<>(6);
        Vertex<Integer> v7 = new Vertex<>(7);
        Vertex<Integer> v8 = new Vertex<>(8);

        v0.setNeighbors(Arrays.asList(v1, v5, v6));
        v1.setNeighbors(Arrays.asList(v3, v4, v5));
        v4.setNeighbors(Arrays.asList(v2, v6));
        v6.setNeighbors(Arrays.asList(v0));
        v3.setNeighbors(Arrays.asList(v7, v8));

        return v0;
    }
}
