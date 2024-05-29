package tree.heap;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public abstract class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    protected T[] heap;
    protected int position = -1;

    public Heap() {
        heap = (T[]) new Comparable[2];
    }

    protected abstract void fixUpward();

    protected abstract void fixDownward(int endIndex);

    @Override
    public HeapInterface<T> insert(T data) {
        if (isFull()) {
            resize(2 * heap.length);
        }
        heap[++position] = data;
        fixUpward();
        return this;
    }

    @Override
    public T getRoot() {
        /*
        -	Replace the root with the last element in the array.
        -	Remove the last element.
        -   Bubble Down (check for heap-properties)
         */
        if (isEmpty()) {
            return null;
        }
        T result = heap[0];
        heap[0] = heap[position--];
        heap[position + 1] = null;
        fixDownward(position);
        return result;
    }

    @Override
    public void sort() {
        /*
        MapHeap Sort (results in Ascending Order)
        - Start from index = 0 (greatest element in MaxHeap)
        - Swap it with the (position - i) index.
          So the greatest element is moved to last
        - Reconstruct the tree (until it obeys Heap-Properties) till (position - i) index.
          So second-largest element is placed at the top of tree
        - Repeat the above 2 steps till last index is reached

        MinHeap Sort (results in Descending Order)
         */
        for (int i = 0; i <= position; i++) {
            swap(0, position - i);
            fixDownward(position - i - 1);
        }
        print();
    }

    public void print() {
        Arrays.stream(heap).forEach(item -> System.out.print(item + " -> "));
        System.out.println();
    }

    protected void swap(int firstIndex, int secondIndex) {
        T temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    private void resize(int capacity) {
        System.arraycopy(heap, 0, heap = (T[]) new Comparable[capacity], 0, position + 1);
    }

    private boolean isFull() {
        return position == heap.length - 1;
    }

    private boolean isEmpty() {
        return heap.length == 0;
    }

}