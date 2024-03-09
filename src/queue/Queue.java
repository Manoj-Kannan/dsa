package queue;

public class Queue<T> {
    private final T[] queue;
    private final int capacity; // max capacity of queue

    private int head = 0;
    private int tail = -1;
    private int size;

    public Queue(int capacity) {
        this.queue = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void enqueue(T data) {
        if (isFull()) {
            System.out.println("Queue is Full!");
            return;
        }
        tail = (tail + 1) % capacity;
        queue[tail] = data;
        size++;
        
        /*
        Why use modulo operator?
        Queue - Capacity = 3, head = 0, tail = -1
        Add an element, head = 0, tail = 0, size = 1
        Add an element, head = 0, tail = 1, size = 2
        Add an element, head = 0, tail = 2, size = 3
        Remove element, head = 1, tail = 2, size = 2
        Add an element, head = 1, tail = 3 --> if tail=3, throws IndexOutOfBoundException (although size=2)

        By using modulo operator, we can add the new element in index=0 (bcoz the element at index 0 is popped)
         */
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        T dataToReturn = queue[head];
        head = (head + 1) % capacity;
        size--;

        return dataToReturn;
    }

    public T peek() {
        return queue[head];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
