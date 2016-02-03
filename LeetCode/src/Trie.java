/**
 * Created by qiqu on 1/21/16.
 */
class TrieNode {
    char c;
    TrieNode[] childrens=new TrieNode[26];
    boolean self=false;
    // Initialize your data structure here.
    public TrieNode() {

    }
    public TrieNode(char c){
        this.c=c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode point=root, pre;
        for (char c:word.toCharArray()){
            if (point.childrens[c-'a']==null) {
                point.childrens[c-'a']=new TrieNode(c);
            }
            point=point.childrens[c-'a'];
        }
        point.self=true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode point=root;
        for (char c:word.toCharArray()){
            point=point.childrens[c-'a'];
            if (point==null) return false;
        }
        return point.self;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode point=root;
        for (char c:prefix.toCharArray()){
            point=point.childrens[c-'a'];
            if (point==null) return false;
        }
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");