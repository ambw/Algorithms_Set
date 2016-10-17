package com.ambw;

/**
 * Created by ambw on 16/8/16.
 */
public class UnionFind {
    private int[] id;   //父链接数组 (触点索引)
    private int[] size;
    private int count; //连通分支数

    public UnionFind(int N) {
        count = N;

        id = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }

    }

    public int count() {
        return this.count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find( int p) {
        int orignP = p;

        while (p != id[p]) p = id[p];

        while (orignP != id[orignP]) {
            orignP = id[orignP];
            id[orignP] = p;
        }

        return p;
    }

    public void union(int p, int q) {
        int proot = find(p);
        int qroot = find(q);

        if (size[proot]  < size[qroot]) {
            id[proot] = id[qroot];
            size[qroot] += size[proot];
        } else {
            id[qroot] = id[proot];
            size[proot] += size[qroot];
        }

        count--;
    }
}
