package tree.trie;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
public class Node {
    // every character in a word
    private final char character;
    // 'car', 'cart', 'careful -> mark true for 'r' and continue with its child... then mark true for 't'
    private boolean isEndOfWord;

    // for above eg. 't', 'e' are children of 'r' node
    @ToString.Exclude
    private Map<Character, Node> children = new HashMap<>();

}

/*
    -	Using arrays to implement tries has several disadvantages:::
        -	Memory Waste
            -	Sparse Representation: Each node in the trie might have a large number of possible children (e.g., 26 for lowercase English letters). Using arrays means allocating space for -	all possible children, even if most of them are not used. This leads to significant memory waste.
            -	Fixed Size: Arrays have a fixed size, which may not be fully utilized. For example, if only a few characters are used at each node, the remaining slots in the array remain -	unused, wasting memory.
        -	Scalability Issues
            -	Limited Alphabet: Using arrays works well when the character set is small and known (like lowercase English letters). However, it becomes impractical for larger or variable -	character sets (e.g., Unicode characters).
            -	Difficulty in Handling Larger Alphabets: If the trie needs to support a larger character set, the array size increases, leading to even more memory consumption.
        -	Insertion and Deletion Complexity
            -	Static Allocation: Arrays require knowing the maximum possible number of children in advance. If the character set changes, resizing the array is complex and inefficient.
            -	Rebalancing Issues: Inserting or deleting nodes might involve shifting elements in the array, which is inefficient for large arrays.
 */
