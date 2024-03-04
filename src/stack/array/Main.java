package stack.array;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> numbers = new Stack<>();

        numbers.push(100);
        numbers.push(200);
        numbers.push(300);

        System.out.println("Peek - " + numbers.peek());

        numbers.pop();
        numbers.pop();
        numbers.pop();

        System.out.println("Peek - " + numbers.peek());

        numbers.push(200);
        numbers.push(300);

        System.out.println("Peek - " + numbers.peek());
    }
}
