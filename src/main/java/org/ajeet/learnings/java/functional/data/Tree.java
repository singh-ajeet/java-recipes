package org.ajeet.learnings.java.functional.data;

import java.util.List;

public interface Tree<K, V> {
    public void add(K key, V value);
    public V update(K key, V newValue);
    public V find(K key);
    public List<V> searchRange(K from, K to);
}
