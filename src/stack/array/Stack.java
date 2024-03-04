package stack.array;

public class Stack<T> {
    // Since we are using array for implementation, we need to resize array accordingly
    // on push, if size of array is full, double the size of array
    // on pop, if (size of stack array/2) < size, reduce the size of stack (to free up memory)
    private T[] stack;
    private int size;

    public Stack() {
        stack = (T[]) new Object[1];
    }

    public void push(T data) {
        if (stack.length == size) {
            resize(2 * size);
        }
        stack[size] = data;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        size--;
        T dataToPop = stack[size];

        if (size != 0 && size <= (stack.length / 4)) {
            resize(stack.length / 2);
        }

        return dataToPop;
    }

    public T peek() {
        return !isEmpty() ? stack[size - 1] : null;
    }

    private void resize(int capacity) {
        T[] newStack = (T[]) new Object[capacity];
        System.arraycopy(stack, 0, newStack, 0, size);
        stack = newStack;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
