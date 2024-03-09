package queue;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> numbers =  new Queue<Integer>(3);

        System.out.println("isFull - " + numbers.isFull());

        numbers.enqueue(1);
        numbers.enqueue(2);
        numbers.enqueue(3);

        System.out.println("isFull - " + numbers.isFull());

        numbers.enqueue(4);

        numbers.dequeue();
        numbers.enqueue(4);

        System.out.println("isFull - " + numbers.isFull());

        numbers.dequeue();
        numbers.dequeue();

        System.out.println("isFull - " + numbers.isFull());

        numbers.dequeue();

        System.out.println("isFull - " + numbers.isFull());
        System.out.println("isEmpty - " + numbers.isEmpty());

    }
}
