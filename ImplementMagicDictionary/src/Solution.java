public class Solution {
    public static void main(String[] args) {
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(md.search("hello"));
        System.out.println(md.search("hhllo"));
        System.out.println(md.search("hell"));
        System.out.println(md.search("leetcoded"));
    }
}
