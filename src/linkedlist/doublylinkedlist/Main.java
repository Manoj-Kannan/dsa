package linkedlist.doublylinkedlist;


public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new DoublyLinkedList<>();

        System.out.println("isEmpty - " + numbers.isEmpty());

        numbers.insertAtTail(30);
        numbers.insertAtTail(40);
        numbers.insertAtHead(20);
        numbers.insertAtHead(10);

        numbers.traverseFromHead();
        System.out.println();
        numbers.traverseFromTail();
        System.out.println();

        numbers.remove(20);
        numbers.remove(40);

        numbers.traverseRecursivelyFromTail();
        System.out.println();

        numbers.insertAtHead(50);

        numbers.traverseRecursivelyFromHead();
        System.out.println();

        numbers.reverse();
        numbers.traverseFromHead();
        System.out.println();

        System.out.println("isEmpty - " + numbers.isEmpty());
    }
}
