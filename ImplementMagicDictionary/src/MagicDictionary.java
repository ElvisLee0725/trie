import java.util.HashMap;

class MagicDictionary {
    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for(String str : dictionary) {
            add(str);
        }
    }

    public boolean search(String searchWord) {
        return searchHelper(searchWord, 0, root, false);
    }

    public boolean searchHelper(String str, int index, TrieNode node, boolean changed) {
        if(index == str.length()) {
            return node.endOfWord && changed;
        }

        if(node.children.containsKey(str.charAt(index))) {
            if(searchHelper(str, index + 1, node.children.get(str.charAt(index)), changed)) {
                return true;
            }
        }

        if(!changed) {
            for(char nextChar : node.children.keySet()) {
                // Only when the next character doesn't equal to the target character, we try to skip it
                if(nextChar != str.charAt(index) && searchHelper(str, index + 1, node.children.get(nextChar), true)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void add(String s) {
        TrieNode cur = root;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode());
            }
            cur = cur.children.get(ch);
        }
        cur.endOfWord = true;
    }
}
