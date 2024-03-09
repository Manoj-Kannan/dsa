package map;

import java.lang.reflect.Array;

/**
 * This class contains HashMap implementation using Chaining Concept (using Array - HashTable, LL - Chaining)
 */
public class HashTable<K, V> {
    // In JVM, default HashTable Size is 16. It is doubled when Load Factor is reached (0.75).
    protected final int MAX_HEAP_SIZE = 3;
    protected HashItem<K, V>[] hashTable;
    protected int size;

    public HashTable() {
        this.hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_HEAP_SIZE);
    }

    public int hash(K key) {
        return key.hashCode() % MAX_HEAP_SIZE;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];

        // Check whether item present in HashTable at same index
        // if same index found, 1 -> if same key, update existing value, 2 -> if new key, insert new pair
        while (item != null) {
            if (item.getKey().equals(key)) {
                item.setValue(value);
                return;
            }
            item = item.getNextHashItem();
        }

        // Add newItem at index 0 of LL of the itemIndex
        HashItem<K, V> newItem = new HashItem<>(key, value);
        newItem.setNextHashItem(hashTable[index]);
        hashTable[index] = newItem;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];
        while (item != null) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
            item = item.getNextHashItem();
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];
        HashItem<K, V> prevItem = null;         // keep track of prevItem (since we make use of LL to map next item)

        while (item != null) {
            if (item.getKey().equals(key)) break;

            prevItem = item;
            item = item.getNextHashItem();
        }

        // item not found
        if (item == null) return null;

        if (prevItem == null) {
            // it is the first element in LL
            hashTable[index] = item.getNextHashItem();
        } else {
            // it is somewhere in between in LL
            prevItem.setNextHashItem(item.getNextHashItem());
        }

        size--;
        return item.getValue();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (size > 0) {
            for (HashItem<K, V> kvHashItem : hashTable) {
                if (kvHashItem != null) {
                    constructPrint(result, kvHashItem);
                    if (kvHashItem.getNextHashItem() != null) {
                        constructPrint(result, kvHashItem.getNextHashItem());
                    }
                } else {
                    result.append(" null -->");
                }
            }
        }
        return result.toString();
    }

    private void constructPrint(StringBuilder result, HashItem<K, V> kvHashItem) {
        result.append(kvHashItem.getKey())
                .append(" : ")
                .append(kvHashItem.getValue())
                .append(" --> ");
    }
}
