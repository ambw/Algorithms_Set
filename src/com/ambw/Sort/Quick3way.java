package com.ambw.Sort;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ambw on 8/26/16.
 */
public class Quick3way extends SortExample {
    public static void sort(Comparable[] a){
        shuffleArray(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int lt = lo,i = lo + 1, gt = hi;
        Comparable v = a[lo];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if(cmp < 0) {
                exch(a,lt++,i++);
            } else if( cmp > 0) {
                exch(a,i,gt--);
            } else {
                i++;
            }
        }

        sort(a,lo,lt-1);
        sort(a,gt + 1,hi);
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
