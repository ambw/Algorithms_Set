package com.ambw.Sort;

/**
 * Created by ambw on 16/8/19.
 */
public class SortExample {

    public static void sort(Comparable[] a) {

    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a,int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (Comparable v: a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])) {
                return false;
            }
        }

        return true;
    }

}
