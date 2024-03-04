package stack.arraylist;

import lombok.Data;
import lombok.ToString;

@Data
public class Node<T> {
    private final T data;
    @ToString.Exclude
    private Node<T> next;
}
