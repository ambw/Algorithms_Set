package com.ambw.Sort;

import java.util.Comparator;

/**
 * Created by ambw on 8/28/16.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
    }


    public boolean isEmpty() {
        return this.N == 0;
    }

    public int size() {
        return this.N;
    }

    public void insert(Key v) {
        if (N >= pq.length - 1) resize(2 * pq.length);

        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {

        Key max = pq[1];
        exch(1,N--);
        pq[N + 1] = null;
        if ((N > 0) && (N == (pq.length - 1) / 4)) resize(pq.length / 2);
        sink(1);
        return max;
    }

    public void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    public void sink(int k) {
        while ( k * 2 <= N) {
            int j = 2 * k;
            if(j  < N && less(j,j + 1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;

    }
}
