package com.ambw.Search;

/**
 * Created by bing0ne on 17/09/2016.
 */
public class LinearProbingHashST<Key, Value> {


    private int N; // 符号表中的键值对总数
    private int M; // 线性探测表
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(int M) {
        this.M = M;
        this.N = 0;
        this.keys = (Key[]) new Object[M];
        this.vals = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % M;
    }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(size);

        for (int i = 0; i <M;i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        M    = temp.M;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2) {
            resize(M * 2);
        }

        int i;

        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }

        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    // 检测是否包含 key
    private boolean contains(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % M;

        // 当当前不为 null 时, 说明他可能受到删除掉的键的影响。
        // 当前为 null 时则表明,他不受影响。
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo,valToRedo);
            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }

    }
}
