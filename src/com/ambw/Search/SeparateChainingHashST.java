package com.ambw.Search;

import com.ambw.ADT.Queue;

import java.util.Iterator;

/**
 * Created by bing0ne on 17/09/2016.
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    private int N;      // 建值总对数
    private int M;      // 散列表的大小
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];

        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (N >= 10*M) resize(2 * M);
        int i = hash(key);
        if (!st[i].contains(key)) {
            st[hash(key)].put(key, val);
            N++;
        }
    }
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }



    public void delete(Key key) {
        st[key.hashCode()].delete(key);
    }

    private void resize(int chains) {

        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < M;i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M = temp.M;
        this.N = temp.N;
        this.st = temp.st;
    }

}
