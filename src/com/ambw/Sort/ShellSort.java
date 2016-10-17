package com.ambw.Sort;

/**
 * Created by ambw on 16/8/20.
 */
public class ShellSort extends SortExample {
    public static void sort(Comparable[] a) {

        int N = a.length;
        int h = 1;

        while (h < N/3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j],a[j-h]);j -= h) {
                    exch(a,j,j-h);
                }
            }

            h = h/3 ;

        }
    }
    public static void main(String[] args) {
        Integer[] a = {5,4,3,2,1};

        sort(a);

        if(isSorted(a)) {
            show(a);
        }
    }
}
