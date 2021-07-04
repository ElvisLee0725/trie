// Given two arrays of strings, one containing the prefixes (area code) and one with a long
// string of numbers (phone numbers), return the longest prefix corresponding to all phone numbers.

// Input: Area Code: ["213", "21358", "1234", "12"]
//        Phone Numbers: ["21349049", "1204539492", "123490485904"]
// Output: ['213', '12', '1234']

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Trie Solution:
// Create a trie for all area codes
//          root
//        /        \
//       1          2
//      /           |
//     2 - end      1
//     |            |
//     3            3 - end
//     |            |
//     4 - end      5
//                  |
//                  8 - end
// For each phone number, find the longest trie and store in result array
// Time: O(N * M) N is the length of phone numbers, M is the length of area code
public class Solution {
    public static void main(String[] args) {
        String [] area = new String[] {"213", "21358", "1234", "12"};
        String [] phone = new String[] {"21349049", "1204539492", "123490485904"};
        System.out.println(new Solution().findLongestPrefix(area, phone));
    }

    class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            this.children = new HashMap<>();
            this.endOfWord = false;
        }
    }
    TrieNode root;
    private List<String> findLongestPrefix(String [] area, String [] phone) {
        root = new TrieNode();
        for(String str : area) {
            addTrie(str);
        }
        List<String> res = new ArrayList<>();
        for(String num : phone) {
            int lastIndex = searchTrie(num);
            res.add(num.substring(0, lastIndex+1));
        }
        return res;
    }

    private void addTrie(String word) {
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

    private int searchTrie(String s) {
        TrieNode cur = root;
        int lastIndex = -1;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(cur.children.containsKey(ch)) {
                cur = cur.children.get(ch);
                if(cur.endOfWord) {
                    lastIndex = i;
                }
            }
            else {
                break;
            }
        }
        return lastIndex;
    }
}
