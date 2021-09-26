package com.jason.lee.learn;

import java.util.HashMap;

/**
 * 字典树（前缀树）
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (!node.children.containsKey(character))
                node.children.put(character, new TrieNode());
            node = node.children.get(character);
        }
        node.wordEnd = true;
    }

    public boolean search(String str) {
        TrieNode node = root;
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (!node.children.containsKey(character))
                return false;
            node = node.children.get(character);
        }
        return true && node.wordEnd;
    }
}

class TrieNode {
    public boolean wordEnd;
    public HashMap<Character, TrieNode> children;

    public TrieNode() {
        this.wordEnd = false;
        this.children = new HashMap<Character, TrieNode>();
    }
}
