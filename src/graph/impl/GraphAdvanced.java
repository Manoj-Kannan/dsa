package graph.impl;

import java.util.*;

public class GraphAdvanced extends Graph {
    public List<String> topologicalSort() {
        /*
        To perform Topological Sort, graph should be "Directed Acyclic Graph"
        Sample Graph : [P, A, B, X] - a list of server
        So,
        To start a server P, A & B should be in running state (dependent)
        To start a server A, X should be in running state
        To start a server B, X should be in running state

        Implementation:
        similar to DFS (using Stack)
        DFS:
        1 -> add a node to Stack
        2 -> pop the parent node (visited) & add all its neighbours to stack (non-visited)
        3 -> loop until stack is empty
        Topological Sort:
        1 -> add a node to Stack
        2 -> add all its neighbours to stack (non-visited)
        3 -> for each new item added to stack, repeat STEP2
        4 -> when all the nodes are visited (graph is fully traversed), pop the items from stack (contains visited nodes) (sorted order obtained)
         */

        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        for (var currNode : nodes.values()) {
            topologicalSort(currNode, visited, stack);
        }

        List<String> sortedList = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedList.add(stack.pop().getLabel());
        }

        return sortedList;
    }

    private void topologicalSort(Node node, Set<Node> visited, Stack<Node> stack) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);

        for (var currChild : adjacencyList.get(node)) {
            topologicalSort(currChild, visited, stack);
        }

        stack.push(node);
    }

    public boolean hasCycle() {
        /*
        Topological Sort cannot be implemented on Cyclic Graphs.
        To check cycle in a graph, we can use DFS.

        Steps:
        1 -> create 3 sets:
             - all(all nodes)
             - visiting(currently visiting nodes) - to keep track of recursion
             - visited(visited nodes)
        2 -> loop over all set and check whether a node hasCycle()
             Recursive Call:
                1 -> remove element from all, add it to visiting
                2 -> visit each neighbour recursively
                     a) if node is in visited set, continue
                     b) if node is in currently visiting set, it has a cycle (starts from a node, traverses via neighbours, reaches parent again)
                3 -> remove from visiting set, add to visited set
         */

        Set<Node> all = new HashSet<>(nodes.values());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty()) {
            var currNode = all.iterator().next();
            if (hasCycle(currNode, all, visiting, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (var neighbour : adjacencyList.get(node)) {
            if (visited.contains(neighbour)) {
                continue;
            }

            if (visiting.contains(neighbour)) {
                return true;
            }

            if (hasCycle(neighbour, all, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }
}
