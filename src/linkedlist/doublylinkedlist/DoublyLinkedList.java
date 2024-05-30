package linkedlist.doublylinkedlist;

public class DoublyLinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void insertAtHead(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty()) {
            head = tail = node;
        } else {
            head.setPrevious(node);
            node.setNext(head);
            head = node;
        }
        size++;
    }

    @Override
    public void insertAtTail(T data) {
        Node<T> node = new Node<>(data);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        size++;
    }

    @Override
    public void remove(T data) {
        if (isEmpty()) {
            System.out.print("List is empty!");
            return;
        } else if (head.getData().equals(data)) {
            head = head.getNext();
            head.setPrevious(null);
        } else if (tail.getData().equals(data)) {
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            Node<T> currNode = head.getNext();
            while (currNode != null) {
                if (currNode.getData().equals(data)) {
                    currNode.getPrevious().setNext(currNode.getNext());
                    currNode.getNext().setPrevious(currNode.getPrevious());
                    break;
                }
                currNode = currNode.getNext();
            }
        }
        size--;
    }

    @Override
    public void traverseFromHead() {
        if (isEmpty()) {
            System.out.print("List is empty!");
        }
        Node<T> currNode = head;
        while (currNode != null) {
            System.out.print(currNode);
            currNode = currNode.getNext();
        }
    }

    @Override
    public void traverseFromTail() {
        if (isEmpty()) {
            System.out.print("List is empty!");
        }
        Node<T> currNode = tail;
        while (currNode != null) {
            System.out.print(currNode);
            currNode = currNode.getPrevious();
        }
    }

    @Override
    public void traverseRecursivelyFromHead() {
        if (isEmpty()) {
            System.out.print("List is empty!");
        }
        traverseRecursivelyFromHead(head);
    }

    private void traverseRecursivelyFromHead(Node<T> node) {
        if (node != null) {
            System.out.print(node);
            traverseRecursivelyFromHead(node.getNext());
        }
    }

    @Override
    public void traverseRecursivelyFromTail() {
        if (isEmpty()) {
            System.out.print("List is empty!");
        }
        traverseRecursivelyFromTail(tail);
    }

    private void traverseRecursivelyFromTail(Node<T> node) {
        if (node != null) {
            System.out.print(node);
            traverseRecursivelyFromTail(node.getPrevious());
        }
    }

    @Override
    public void reverse() {
        // Mark the nextNode as previous, previousNode as next recursively from head to tail.
        // At the end, head is marked as tail & tail is marked as head
        Node<T> oldHead = head;
        Node<T> currNode = head;
        Node<T> tempNode = null;
        while (currNode != null) {
            tempNode = currNode.getPrevious();

            currNode.setPrevious(currNode.getNext());
            currNode.setNext(tempNode);

            currNode = currNode.getPrevious();
        }

        if (tempNode != null) head = tempNode.getPrevious();
        tail = oldHead;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
