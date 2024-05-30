package tree.trie;

import java.util.List;

public interface TrieInterface {

    TrieInterface insert(String word);

    boolean contains(String word);

    void delete(String word);

    boolean containsPrefix(String prefix);

    List<String> wordsWithPrefix(String prefix);

}
