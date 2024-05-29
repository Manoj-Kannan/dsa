package tree.heap;

public interface HeapInterface<T extends Comparable<T>> {

    HeapInterface<T> insert(T data);

    // delete root element
    T getRoot();

    void sort();

}