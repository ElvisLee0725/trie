import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Trie
// Create TrieNode and build up a Trie data structure based on the Strings in products
// Example 1: ["mobile","mouse","moneypot","monitor","mousepad"]
/*
                m
                |
                o
         /      |      \
         b.     n.      u
         |     /. \     |
         i    e.   i    s
         |    |    |    |
         l    y    t    e-
         |    |    |    |
         e-   p    o    p
              |    |    |
              o    r-   a
              |         |
              t-        d-

*/
// For each letter in the searchWord is typed, find the prefix of the searchWord in the Trie
// Start from that TrieNode, find 3 closest words in the Trie and store them in the result
// Keep doing until every letter in searchWord has been used as prefix
// Ex. m, mo, mou, mous, mouse
class Solution {
    public static void main(String[] args) {
        List<List<String>> res = new Solution().suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"}, "mouse");
        System.out.println(res);
    }
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            this.children = new TreeMap<>();
            this.endOfWord = false;
        }
    }

    TrieNode root = new TrieNode();
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for(String str : products) {
            addWord(str);
        }

        StringBuilder prefix = new StringBuilder();
        List<List<String>> res = new ArrayList();

        for(int i = 0; i < searchWord.length(); i++) {
            prefix.append(searchWord.charAt(i));
            List<String> list = new ArrayList();
            getPrefixNode(prefix.toString(), list);
            res.add(list);
        }
        return res;
    }

    // Case 1: Such prefix doesn't exist in Trie, return empty list
    // Case 2: Start from this prefix, traverse pre-order and find at most 3 words to add to list
    public void getPrefixNode(String prefix, List<String> list) {
        TrieNode cur = root;
        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if(!cur.children.containsKey(ch)) {
                return ;
            }
            cur = cur.children.get(ch);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        findClosetWords(cur, sb, list);
    }

    public void findClosetWords(TrieNode node, StringBuilder sb, List<String> list) {
        if(list.size() == 3) {
            return;
        }

        if(node.endOfWord) {
            list.add(sb.toString());
        }

        for(Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            sb.append(entry.getKey());
            findClosetWords(entry.getValue(), sb, list);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public void addWord(String word) {
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

}
