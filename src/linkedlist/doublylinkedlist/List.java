package linkedlist.doublylinkedlist;

public interface List<T> {
    void insertAtHead(T data);
    void insertAtTail(T data);
    void remove(T data);
    void traverseFromHead();
    void traverseFromTail();
    void traverseRecursivelyFromHead();
    void traverseRecursivelyFromTail();
    void reverse();
    boolean isEmpty();
}
