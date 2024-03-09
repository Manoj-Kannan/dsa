package map;

public class Main {
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("A", 1);
        hashTable.put("B", 2);
        hashTable.put("C", 3);

        System.out.println(hashTable);

        hashTable.put("A", 4);
        hashTable.put("E", 5);

        System.out.println(hashTable);

        hashTable.remove("A");

        System.out.println(hashTable);

        hashTable.get("A");

        System.out.println(hashTable);

        System.out.println("---------------------------------------------------------");

        HashTableLinearProbe<String, Integer> hashTableLinearProbe = new HashTableLinearProbe<>();
        hashTableLinearProbe.put("A", 1);
        hashTableLinearProbe.put("B", 2);
        hashTableLinearProbe.put("C", 3);

        System.out.println(hashTableLinearProbe);

        hashTableLinearProbe.put("A", 4);
        hashTableLinearProbe.put("E", 5);

        System.out.println(hashTableLinearProbe);

        hashTableLinearProbe.remove("A");

        System.out.println(hashTableLinearProbe);

        hashTableLinearProbe.get("A");

        System.out.println(hashTableLinearProbe);
    }
}
