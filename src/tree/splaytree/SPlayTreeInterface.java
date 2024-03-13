package tree.splaytree;

import tree.Tree;

public interface SPlayTreeInterface<T extends Comparable<T>> extends Tree<T> {
    Node<T> find(T data);

    Node<T> findRecursively(T data);
}
