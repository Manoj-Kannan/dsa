package tree.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie implements TrieInterface {
    /*
    Sample Structure - words "hello", "hell", "heaven", and "heavy".
                    (root)
                    /    \
                   h      h
                  /      / \
                 e      e   e
                / \      \   \
               l   a      a   a
              / \   \      \   \
             l   v   v      v   v
            /     \   \      \   \
           o       e   y      e   y
           |        |   |      |  |
         (end)     n  (end)   n  (end)
                   |           |
                 (end)        v
                               |
                             (end)

     */

    private final Node root;

    public Trie() {
        root = new Node(' ');
    }

    @Override
    public TrieInterface insert(String word) {
        Node currentNode = root;
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (children.containsKey(c)) {
                currentNode = children.get(c);
            } else {
                currentNode = new Node(c);
                children.put(c, currentNode);
            }
            children = currentNode.getChildren();
        }
        currentNode.setEndOfWord(true);
        return this;
    }

    @Override
    public boolean contains(String word) {
        Node lastPresentNode = search(word);
        return lastPresentNode != null && lastPresentNode.isEndOfWord();
    }

    @Override
    public boolean containsPrefix(String prefix) {
        return search(prefix) != null;
    }

    private Node search(String str) {
        Node currentNode = null;
        Map<Character, Node> children = root.getChildren();
        for (char c : str.toCharArray()) {
            if (!children.containsKey(c)) {
                return null;
            }
            currentNode = children.get(c);
            children = currentNode.getChildren();
        }
        return currentNode;
    }

    @Override
    public List<String> wordsWithPrefix(String prefix) {
        /*
        Source Map  -> car, careful, cart
        prefix      -> car

        Steps:
        1 -> find last node, node 'r'
        2 -> get its children -> 'e', 't'
        3 -> loop over the child nodes and repeat steps 1 & 2
             -> append 'car' with 'e' and repeat steps 1 & 2
             -> append 'car' with 't' and repeat steps 1 & 2
         */
        List<String> words = new ArrayList<>();
        Node prefixNode = search(prefix);
        if (prefixNode != null) {
            addWords(prefixNode, prefix, words);
        }
        return words;
    }

    private void addWords(Node node, String word, List<String> wordList) {
        if (node.isEndOfWord()) {
            wordList.add(word);
        }
        for (Node child : node.getChildren().values()) {
            String character = String.valueOf(child.getCharacter());
            addWords(child, word.concat(character), wordList);
        }
    }

    @Override
    public void delete(String word) {
        /*
        Source Map  - applicative, application
        To delete   - applicative

        Steps:
        1 -> Get all nodes in the word 'a', 'p', 'p', 'l', ... 'v', 'e'
        2 -> set endOfWord - false for last node 'e'
        3 -> loop from bottom to top (so that we can check the sharing of character between words)
             cases:
             1) current node is end of other word -> stop deletion of node
             2) no children found -> delete node
         */
        List<Node> list = new ArrayList<>();
        Map<Character, Node> children = root.getChildren();
        for (char c : word.toCharArray()) {
            if (!children.containsKey(c)) {
                break;
            }
            Node currentNode = children.get(c);
            children = currentNode.getChildren();
            list.add(currentNode);
        }

        if (list.isEmpty() || list.size() != word.length()) {
            return;
        }
        list.get(list.size() - 1).setEndOfWord(false);

        for (int i = list.size() - 1; i > 0; i--) {
            Node current = list.get(i);
            if (current.isEndOfWord()) {
                break;
            } else if (current.getChildren().isEmpty()) {
                list.get(i - 1).getChildren().remove(current.getCharacter());
            }
        }
    }
}
