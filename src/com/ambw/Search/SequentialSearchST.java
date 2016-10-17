package com.ambw.Search;

import com.ambw.ADT.Queue;


/**
 * Created by ambw on 8/30/16.
 */

public class SequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;
        public Node(Key key,Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }

        return null;
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(value)) return;

            first = new Node(key,value,first);
        }
    }

    public void delete(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x = x.next;
            }
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
}


