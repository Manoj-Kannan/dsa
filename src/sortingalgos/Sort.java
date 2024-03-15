package sortingalgos;

import lombok.Data;

@Data
public abstract class Sort<T extends Comparable<T>> {
    protected T[] arr;

    public Sort(T[] arr) {
        this.arr = arr;
    }

    public abstract void sort();
}
