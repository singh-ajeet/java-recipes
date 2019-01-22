package org.ajeet.learnings.java.functional.data;

import java.util.List;
import java.util.Objects;

public final class BinaryTree<K, V> implements Tree<K, V> {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    @Override
    public void add(K key, V value) {
        Objects.requireNonNull(key, "key cant be null");
    }

    @Override
    public V update(K key, V newValue) {
        return null;
    }

    @Override
    public V find(K key) {
        return null;
    }

    @Override
    public List<V> searchRange(K from, K to) {
        return null;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private Node left = null;
        private Node right = null;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private Node getLeft() {
            return left;
        }

        private Node getRight() {
            return right;
        }

        private void setValue(V value) {
            this.value = value;
        }

        private void setLeft(Node left) {
            this.left = left;
        }

        private void setRight(Node right) {
            this.right = right;
        }
    }
}
