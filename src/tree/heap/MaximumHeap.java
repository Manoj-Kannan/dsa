package tree.heap;

public class MaximumHeap<T extends Comparable<T>> extends Heap<T> {

    protected void fixUpward() {
        /*
        -	Add the new element at the end of the array.
        -	Heapify up: Compare the added element with its parent; if the added element is larger (for max heap), swap them.
            Repeat until the heap property is restored.
         */
        int index = position;
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    protected void fixDownward(int endIndex) {
        /*
        param - endIndex - lastIndex to check for maximum element
        Steps:
        1 -> Start from index 0.
        2 -> Get the children of parent (index 0)
        3 -> Get valid element to compare with element (the non-null child (or) greater among 2 child)
        4 -> Compare greater value (obtained above) with the value in currIndex (index 0)
        5 -> If the child is greater, swap is required.
             Or,  the tree obeys the heap-properties
        6 -> continue...
         */
        if (endIndex == -1) {
            return;
        }

        int index = 0;
        while (index <= endIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (leftChildIndex > endIndex) {
                break;
            }

            int childToSwap;
            if (rightChildIndex > endIndex) {
                childToSwap = leftChildIndex;
            } else {
                if (heap[leftChildIndex].compareTo(heap[rightChildIndex]) > 0) {
                    childToSwap = leftChildIndex;
                } else {
                    childToSwap = rightChildIndex;
                }
            }

            if (heap[index].compareTo(heap[childToSwap]) > 0) {
                break;
            }
            swap(index, childToSwap);
            index = childToSwap;
        }
    }

}
