package doublylinkedlist;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Node<T> {
    private final T data;
    private Node<T> next;
    private Node<T> previous;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString() + "->";
    }
}
