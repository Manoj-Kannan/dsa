package linkedlist;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new LinkedList<>();

        System.out.println("isEmpty - " + numbers.isEmpty());

        numbers.insert(10);
        numbers.insert(20);
        numbers.insert(30);
        numbers.insert(40);

        numbers.traverse();

        numbers.delete(20);

        numbers.traverse();

        System.out.println("isEmpty - " + numbers.isEmpty());
    }

}
