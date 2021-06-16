import java.util.HashMap;
import java.util.Map;

// Trie
class TrieNode {
    Map<Character, TrieNode> children;
    boolean endOfWord;
    public TrieNode() {
        children = new HashMap();
        endOfWord = false;
    }
}
class WordDictionary {
    TrieNode trieRoot;
    /** Initialize your data structure here. */
    public WordDictionary() {
        trieRoot = new TrieNode();
    }

    // Start from root. Iterate the word and see if cur character is in trie? If not, add it. Else, move on
    // Only mark endOfWord to true at the last character of a word
    // Time: O(m), Space: O(m) while m is the length of input word
    public void addWord(String word) {
        TrieNode node = trieRoot;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.endOfWord = true;
    }

    // Check each character in word
    // If current trie's children doesn't contain the cur character:
    // Check if it's a '.'? If so, try all possilbe char in children and call recursion. Return true if it is true
    // Else, we cannot find this word, return false
    // Else, Keep moving to the next trie node
    // At the end, return node.word to see if current trie node is the end of a word
    // Time: For well defined word: O(m), for words with dot: O(n * 26^m)
    // Space: O(1) for well defined word, O(m) call stack for dot word
    public boolean search(String word) {
        return trieSearch(word, trieRoot);
    }

    public boolean trieSearch(String word, TrieNode node) {
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)) {
                if(ch == '.') {
                    for(Character c : node.children.keySet()) {
                        if(trieSearch(word.substring(i+1), node.children.get(c))) {
                            return true;
                        }
                    }
                }
                return false;
            }
            else {
                node = node.children.get(ch);
            }
        }
        return node.endOfWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
