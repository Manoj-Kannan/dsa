package linkedlist;

public class LinkedList<T> implements List<T> {
    private Node<T> root;
    private int size;

    @Override
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        node.setNextNode(root);
        root = node;
        size++;
    }

    @Override
    public void delete(T date) {
        if (root.getData().equals(date)) {
            root = root.getNextNode();
        } else {
            Node<T> previousNode = root;
            Node<T> currentNode = root.getNextNode();
            while (currentNode != null) {
                if (currentNode.getData().equals(date)) {
                    previousNode.setNextNode(currentNode.getNextNode());
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
        }
        size--;
    }

    @Override
    public void traverse() {
        if (isEmpty()) {
            System.out.println("List is empty!");
        }
        traverse(root);
    }

    private void traverse(Node<T> node) {
        if (node != null) {
            System.out.println(node);
            traverse(node.getNextNode());
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
