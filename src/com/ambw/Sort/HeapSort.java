package com.ambw.Sort;

/**
 * Created by ambw on 8/28/16.
 */
public class HeapSort extends SortExample{

    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int k = N/2; k >=1 ; k-- ) {
            sink(a,k,N);
        }

        while (N > 1) {
            HeapSort.exch(a,1,N--);
            HeapSort.sink(a,1,N);
        }

    }

    public static void sink(Comparable[] a, int k, int N) {
        while ( k * 2 <= N) {
            int j = 2 * k;
            if(j  < N && HeapSort.less(a,j,j+1)) j++;
            if(!HeapSort.less(a,k,j)) break;
            HeapSort.exch(a,k,j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    public static void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    public static void main(String[] args) {
        Integer[] a = {5,4,3,2,1};

        sort(a);

        if(SelectSort.isSorted(a)) {
            show(a);
        }
    }

}
