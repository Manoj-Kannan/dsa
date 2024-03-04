package stack.arraylist;

public class Stack<T> {
    private Node<T> root;
    private int size;

    public void push(T data) {
        Node<T> node = new Node<>(data);
        if (!isEmpty()) {
            node.setNext(root);
        }
        root = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        T rootData = root.getData();
        root = root.getNext();
        size--;
        return rootData;
    }

    public T peek() {
        return !isEmpty() ? root.getData() : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
