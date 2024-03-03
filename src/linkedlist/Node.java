package linkedlist;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Data
public class Node<T> {
    private final T data;
    @ToString.Exclude
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
    }
}
