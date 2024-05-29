package graph.dfs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch<T> {

    // This one will visit the last child first
    public void traverse(Vertex<T> startVertex) {
        Stack<Vertex<T>> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();
            if (!current.isVisited()) {
                current.setVisited(true);
                System.out.println(current);
                // to preserve order of child without recursive approach (we can reverse the neighbours before push to stack
                // (since stack follows LIFO)
                current.getNeighbors().forEach(stack::push);
            }
        }
    }

    // This one will preserve the order of the children
    public void traverseRecursively(Vertex<T> vertex) {
        vertex.setVisited(true);
        System.out.println(vertex);
        for (Vertex<T> neighbor : vertex.getNeighbors()) {
            if (!neighbor.isVisited()) {
                traverseRecursively(neighbor);
            }
        }
    }
}
