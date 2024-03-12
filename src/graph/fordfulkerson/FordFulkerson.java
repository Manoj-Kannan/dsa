package graph.fordfulkerson;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.stream.IntStream;

public class FordFulkerson<T> {
    private final List<List<Vertex<T>>> paths = new ArrayList<>();

    public int run(Vertex<T> source, Vertex<T> destination) {
        /*
        Steps:
        1 -> To get Maximum Flow, we sort all possible paths in descending order. (bcoz path with maximum flow must be utilised first)
             Calculated by finding the minimum capacity in the path (it represents the maximum flow in the path)
             Sort the Paths List

         2 -> Iterate over sorted Paths
              Find the minimum capacity in the path (its the capacity of the path)

         3 -> Add it to MaxFlow
              Update Capacity of each vertex in the path (subtract the minimum vertex from all vertex)
         */

        // Step 1
        findAllPaths(source, destination, new ArrayList<>(Collections.singleton(source)));

        // Step 2
        List<List<Vertex<T>>> sortedPaths = new ArrayList<>(paths);
        sortedPaths.sort(Comparator.comparingInt(this::getMinFlowInPath).reversed());

        Integer maxFlow = 0;
        for (List<Vertex<T>> path : sortedPaths) {
            // Step 3
            Integer minimum = getMinFlowInPath(path);
            IntStream.range(0, path.size() - 1).forEach(vertexIdx -> removeMinFlowFromVerticesInPath(path, minimum, vertexIdx));
            maxFlow += minimum;
        }
        return maxFlow;
    }

    private Integer getMinFlowInPath(List<Vertex<T>> path) {
        /*
        Consider a path S -> A -> B -> D -> C -> T
        For S, Get the Weight to A = 15 (consider as least weight)
        For A, Get the Weight to B = 10 (consider as least weight)
        For B, Get the Weight to D = 14
        For D, Get the Weight to C = 10
        For C, Get the Weight to T = 25
         */
        Integer leastWeight = null;
        boolean isFirstVertex = false;
        int pathLength = path.size() - 1;

        for (int vertexIdx = 0; vertexIdx < pathLength; vertexIdx++) {
            Integer neighborEdgeWeight = getNeighborEdgeWeight(path, vertexIdx);
            if (!isFirstVertex || neighborEdgeWeight.compareTo(leastWeight) < 0) {
                isFirstVertex = true;
                leastWeight = neighborEdgeWeight;
            }
        }

        return isFirstVertex ? leastWeight : 0;
    }

    private void removeMinFlowFromVerticesInPath(List<Vertex<T>> path, Integer min, int vertexIdx) {
        /*
        Update Capacity of each vertex in the path (subtract the minimum vertex from all vertex)
         */
        path.get(vertexIdx).getNeighbors().put(
                path.get(vertexIdx + 1),
                getNeighborEdgeWeight(path, vertexIdx) - min
        );
    }

    private Integer getNeighborEdgeWeight(List<Vertex<T>> path, int vertexIdx) {
        /*
        Consider a path S -> A -> B -> D -> C -> T
        For Vertex S, Index is 0, So Neighbouring weight is the weight from S to A = 15
         */
        return path.get(vertexIdx).getNeighbors().get(path.get(vertexIdx + 1));
    }

    private void findAllPaths(Vertex<T> current, Vertex<T> destination, List<Vertex<T>> currentPath) {
        if (current.equals(destination)) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        current.setVisited(true);
        for (Vertex<T> neighbor : current.getNeighbors().keySet()) {
            if (!neighbor.isVisited()) {
                currentPath.add(neighbor);
                findAllPaths(neighbor, destination, currentPath);
                currentPath.remove(neighbor);
            }
        }
        current.setVisited(false);
    }

    /*
    IntStream(fromIndex, toIndex) -> Represents a for loop stream with start & end index
     */
}
