package com.ambw.Search;

import java.util.Queue;

/**
 * Created by ambw on 9/1/16.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return this.N;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <=hi) {
            int mid = lo + (lo - hi) / 2;
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return lo;

    }

    public void put (Key key, Value val) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] =val;
            return;
        }

        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    private boolean isEmpty(){
        if (N == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void delete(Key key) {
        if(isEmpty()) return;

        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
            }

            N--;
        }
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N];
    }

    public Key select(int k) {
        if(k < 0 && k >= N) return null;
        return keys[k];
    }

    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("argument to ceiling() is null");
        int i = rank(key);
        if (i == N) return null;
        return keys[i];
    }

    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("argument to floor() is null");
        int i = rank(key);

        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];

    }


}
