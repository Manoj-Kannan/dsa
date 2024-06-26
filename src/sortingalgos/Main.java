package sortingalgos;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = { 10, 55, -5, 34, 7, 22, 19 };
        String [] strings = { "Sylvanas", "Arthas", "Illidan", "Thrall", "Jaina" };
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));

        sortAndPrint(new BubbleSort<>(integers.clone()));
        sortAndPrint(new BubbleSort<>(strings.clone()));

        sortAndPrint(new SelectionSort<>(integers.clone()));
        sortAndPrint(new SelectionSort<>(strings.clone()));

        sortAndPrint(new InsertionSort<>(integers.clone()));
        sortAndPrint(new InsertionSort<>(strings.clone()));

        sortAndPrint(new ShellSort<>(integers.clone()));
        sortAndPrint(new ShellSort<>(strings.clone()));

        sortAndPrint(new MergeSortSubArray<>(integers.clone()));
        sortAndPrint(new MergeSortSubArray<>(strings.clone()));

        sortAndPrint(new MergeSortTempArray<>(integers.clone()));
        sortAndPrint(new MergeSortTempArray<>(strings.clone()));

        sortAndPrint(new QuickSort<>(integers.clone()));
        sortAndPrint(new QuickSort<>(strings.clone()));
    }

    private static <T extends Comparable<T>> void sortAndPrint(Sort<T> sortingAlgorithm) {
        sortingAlgorithm.sort();
        System.out.println(Arrays.toString(sortingAlgorithm.arr));
    }
}
