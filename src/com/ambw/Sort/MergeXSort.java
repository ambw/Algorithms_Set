package com.ambw.Sort;

/**
 * Created by ambw on 16/8/20.
 * merge sort + insert sort
 */
public class MergeXSort extends SortExample{
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0,a.length -1);
        assert(isSorted(a));
    }
    public static void sort(Comparable[] a, int lo, int hi){
        if( hi <= lo) {
            return;
        }
        if(hi -lo >15) {
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid);
            sort(a, mid + 1, hi);
            if(less(a[mid + 1],a[mid])){
                merge(a, lo, mid, hi);
            }
        } else {

            insert(a,lo,hi);
            return;
        }

    }

    public static void merge(Comparable[] a, int lo,int mid, int hi) {
        int i  = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if(i >  mid) {
                a[k] = aux[j++];
            } else if(j > hi) {
                a[k] = aux[i++];
            } else if(less(aux[i],aux[j])) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void insert(Comparable[] a,int lo,int hi) {
        int N = lo - hi + 1; // 数组长度
        for (int i = lo; i < hi + 1; i++) {
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5,4,3,2,1,1,5,62,1,7,3,7,2,7,9,1,7,9,9};

        sort(a);

        if(isSorted(a)) {
            show(a);
        }
    }
}
