package tree.heap;

public interface HeapInterface<T extends Comparable<T>> {

    HeapInterface<T> insert(T data);

    T getRoot();

    void sort();

}