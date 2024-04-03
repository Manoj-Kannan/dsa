package sortingalgos;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    public QuickSort(T[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        quicksort(0, arr.length - 1);
    }

    private void quicksort(int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(low, high);
        quicksort(low, pivot - 1);
        quicksort(pivot + 1, high);
    }

    private int partition(int low, int high) {
        int i = low;
        T pivot = arr[high];

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                swap(i, j);
                i++;
            }
        }

        swap(i, high);
        /*
        Explanation:
        1   ->  The pivot element is chosen as the last element of the partitioned array
        2   ->  This index keeps track of the position where elements less than or equal to the pivot will be placed
        3   ->  Iterate through the elements of the partition from low to high - 1.
                At each iteration:
                    If the current element (arr[j]) is less than or equal to the pivot, swap arr[i] with arr[j] and increment i.
                    This step ensures that all elements less than or equal to the pivot are moved to the left side of the partition.
        4   ->  This step places the pivot in its correct position in the sorted array.
                Elements to the left of the pivot are less than or equal to it, and elements to the right are greater than it.
         */
        return i;
    }

    private void swap(int firstIndex, int secondIndex) {
        if (firstIndex != secondIndex) {
            T temp = arr[firstIndex];
            arr[firstIndex] = arr[secondIndex];
            arr[secondIndex] = temp;
        }
    }
}
