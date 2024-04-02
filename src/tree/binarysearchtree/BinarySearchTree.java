package tree.binarysearchtree;

import tree.Tree;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        }
        return node;
    }

    /*
    Deletion Operation:
    Case I
        The node to be deleted is the leaf node. In such a case, simply delete the node from the tree.
    Case II
        The node to be deleted lies has a single child node. In such a case follow the steps below:
            -   Replace that node with its child node (copy the value to the child node & delete the child node, bcoz deleting leaf nodes is easy).
            -   Remove the child node from its original position.
   Case III
        The node to be deleted has two children. In such a case follow the steps below:
            -   Get the inorder predecessor of that node (maximum in current root).
            -   Replace the node with the inorder predecessor (copy the value to the predecessor node & delete the predecessor node).
            -   Remove the inorder predecessor from its original position.
            Note:
                -   Longest left node or Predecessor
                -   Smallest right node or Successor
     */

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // One child or No children
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            // Two children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        return node;
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.println(node);
        traverseInOrder(node.getRightChild());
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

}