package com.ambw.Sort;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ambw on 8/26/16.
 */
public class QuickSort extends SortExample {
    public static void sort(Comparable[] a){
        shuffleArray(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        if(lo >= hi) {
            return;
        }
        int j = partition(a,lo,hi);
        sort(a,lo,j - 1);
        sort(a,j + 1,hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {

        int i = lo, j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i],v)) if(i == hi) break;
            while (less(v,a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);

        return j;
    }

    public static void shuffleArray(Comparable[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Comparable a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
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
