package map;

import java.lang.reflect.Array;

/**
 * This class contains HashMap implementation using Linear Probing (currIndex + 1) Concept (involves resizing of array since there is a search for new index for a key)
 */
public class HashTableLinearProbe<K, V> extends HashTable<K, V> {
    protected int capacity;

    public HashTableLinearProbe() {
        this.hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_HEAP_SIZE);
        this.capacity = MAX_HEAP_SIZE;
    }

    public HashTableLinearProbe(int capacity) {
        this.hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, capacity);
        this.capacity = capacity;
    }

    public void put(K key, V value) {
        if (size >= capacity * 0.75) {
            resize(2 * capacity);
        }

        int index = hash(key);
        while (hashTable[index] != null) {
            if (hashTable[index].getKey().equals(key)) {
                hashTable[index].setValue(value);
                return;
            }
            index = applyProbe(index);
        }
        hashTable[index] = new HashItem<>(key, value);
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        while (hashTable[index] != null) {
            if (hashTable[index].getKey().equals(key)) {
                return hashTable[index].getValue();
            }
            index = applyProbe(index);
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];

        while (item != null && !item.getKey().equals(key)) {
            index = applyProbe(index);
            item = hashTable[index];
        }

        // item not found
        if (item == null) return null;

        V valueToReturn = item.getValue();
        hashTable[index] = null;
        size--;

        // if we mark hashTable[index] = null, then we should reHash all the elements next to current index
        // Bcoz, while we put() we applyProbe to index and insert element, so we should reHash

        while (hashTable[index = applyProbe(index)] != null) {
            HashItem<K, V> nextItem = hashTable[index];
            hashTable[index] = null;

            put(nextItem.getKey(), nextItem.getValue());
        }
        return valueToReturn;
    }

    private int applyProbe(int index) {
        // using module operator to avoid IndexOutOffBound Exception (similar to Queue impl)
        return (index + 1) % capacity;
    }

    private void resize(int newCapacity) {
        // Similar to Stack Impl suing Array (JVM resizes HashTable if Load Factor is reached)
        // Why reHash? if a item is removed, hashTable[i] is set to null, so we need to reHash the hashTable
        // (on put, new index can be assigned to a key based on vacancy on removing unwanted key)
        HashTableLinearProbe<K, V> tempTable = new HashTableLinearProbe<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null) {
                tempTable.put(hashTable[i].getKey(), hashTable[i].getValue());
            }
        }
        hashTable = tempTable.hashTable;
        capacity = newCapacity;
    }
}
