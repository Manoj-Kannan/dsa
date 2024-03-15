package sortingalgos;

import java.util.stream.IntStream;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    public SelectionSort(T[] arr) {
        super(arr);
    }

    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public void sortWithStreams() {
        IntStream.range(0, arr.length - 1).forEach(i ->
                IntStream.range(i, arr.length)
                        .reduce((left, right) -> arr[left].compareTo(arr[right]) < 0 ? left : right)
                        .ifPresent(minIndex -> {
                            T temp = arr[i];
                            arr[i] = arr[minIndex];
                            arr[minIndex] = temp;
                        })
        );
    }

}
