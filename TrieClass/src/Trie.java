import java.util.HashMap;

class Trie {
    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap();
            endOfWord = false;
        }
    }
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    TrieNode root;
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode());
            }
            cur = cur.children.get(ch);
        }
        cur.endOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!cur.children.containsKey(ch)) {
                return false;
            }
            cur = cur.children.get(ch);
        }
        return cur.endOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(!cur.children.containsKey(ch)) {
                return false;
            }
            cur = cur.children.get(ch);
        }
        return true;
    }
}
