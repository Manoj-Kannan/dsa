package graph.dijkstra;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class Dijkstra<T> {
    public void calculateShortestPath(Node<T> source) {
        source.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>();
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node<T> currentNode = unsettledNodes.poll();
            for (Map.Entry<Node<T>, Integer> entry : currentNode.getAdjacentNodes().entrySet()) {
                if (!settledNodes.contains(entry.getKey())) {
                    evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                    unsettledNodes.add(entry.getKey());
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node<T> adjacentNode, Integer edgeWeight, Node<T> sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(),
                                Stream.of(sourceNode)).collect(Collectors.toList()));
        }
    }

    public void printPaths(List<Node<T>> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath().stream()
                    .map(Node::getName).map(Objects::toString)
                    .collect(Collectors.joining(" -> "));

            System.out.println((path.isEmpty()
                    ? String.format("%s : %s", node.getName(), node.getDistance())
                    : String.format("%s -> %s : %s", path, node.getName(), node.getDistance()))
            );
        });
    }
}
