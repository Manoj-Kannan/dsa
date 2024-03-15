package sortingalgos;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] integers = { 10, 55, -5, 34, 7, 22, 19 };
        String [] strings = { "Sylvanas", "Arthas", "Illidan", "Thrall", "Jaina" };
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));

        new BubbleSort<>(integers).sort();
        new BubbleSort<>(strings).sort();
        System.out.println(Arrays.toString(integers) + " | " + Arrays.toString(strings));
    }
}
