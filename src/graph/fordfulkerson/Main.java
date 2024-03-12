package graph.fordfulkerson;

import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Vertex<String> S = new Vertex<>("S");
        Vertex<String> A = new Vertex<>("A");
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");
        Vertex<String> T = new Vertex<>("T");

        Map<Vertex<String>, Integer> sNeighbors = new HashMap<>();
        sNeighbors.put(A, 15);
        sNeighbors.put(B, 12);
        S.setNeighbors(sNeighbors);

        Map<Vertex<String>, Integer> aNeighbors = new HashMap<>();
        aNeighbors.put(B, 10);
        aNeighbors.put(C, 12);
        aNeighbors.put(D, 1);
        A.setNeighbors(aNeighbors);

        Map<Vertex<String>, Integer> bNeighbors = new HashMap<>();
        bNeighbors.put(D, 14);
        B.setNeighbors(bNeighbors);

        Map<Vertex<String>, Integer> cNeighbors = new HashMap<>();
        cNeighbors.put(T, 25);
        C.setNeighbors(cNeighbors);

        Map<Vertex<String>, Integer> dNeighbors = new HashMap<>();
        dNeighbors.put(C, 10);
        dNeighbors.put(T, 4);
        D.setNeighbors(dNeighbors);

        System.out.print("The Max Flow is: ");
        System.out.println(new FordFulkerson<String>().run(S, T));
    }
}
