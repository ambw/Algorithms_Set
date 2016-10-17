package com.ambw.Sort;

/**
 * Created by ambw on 16/8/19.
 */
public class InsertSort extends SortExample {
    public static void sort(Comparable[] a) {
        int N = a.length; // 数组长度
        for (int i = 1; i < N; i++) {
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5,4,3,2,1};

        sort(a);

        if(SelectSort.isSorted(a)) {
            show(a);
        }
    }
}
