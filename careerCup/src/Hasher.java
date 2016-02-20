import sun.awt.image.ImageWatched;

import java.util.ArrayList;

public class Hasher<K, V> {
    private static class LinkedListNode<K, V> {
        public LinkedListNode<K, V> next;
        public LinkedListNode<K, V> prev;

        public K key;
        public V value;

        public LinkedListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<LinkedListNode<K, V>> arr;

    public Hasher(int capacity) {
        arr = new ArrayList<LinkedListNode<K, V>>();
        arr.ensureCapacity(capacity);
        for (int i = 0; i < capacity; i++) {
            arr.add(null);
        }
    }

    public void put(K key, V value) {
        // 1.) If key exists then update the value
        // 2.) Create a new key with the value
        LinkedListNode<K, V> curr = getNodeForKey(key);
        if (curr != null) {
            curr.value = value;
        } else {
            int index = getIndexForKey(key);
            curr = arr.get(index);
            curr.value = value;
        }
    }

    public V remove(K key, V value) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        if (node != null) {
            LinkedListNode<K, V> prev = node.prev;
            prev.next = node.next;
            node.prev = null;
            node.next = null;
            return node.value;
        }
        return null;
    }

    public V get(K key) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        return (node == null) ? null : node.value;

    }

    private LinkedListNode<K, V> getNodeForKey(K key) {
        int index = getIndexForKey(key);
        LinkedListNode<K, V> curr = arr.get(index);
        while (curr != null) {
            if (curr.key == key) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    private int getIndexForKey(K key) {
        return Math.abs(key.hashCode() % arr.size());
    }


}
