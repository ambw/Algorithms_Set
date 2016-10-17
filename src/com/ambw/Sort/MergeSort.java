package com.ambw.Sort;

/**
 * Created by ambw on 16/8/20.
 */
public class MergeSort extends SortExample {
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

        int mid = lo + (hi - lo)/2;
        sort(a,lo,mid);
        sort(a,mid + 1, hi);
        if(less(a[mid + 1],a[mid])){
            merge(a, lo, mid, hi);
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

    public static void main(String[] args) {
        Integer[] a = {5,4,3,2,1};

        sort(a);

        if(isSorted(a)) {
            show(a);
        }
    }
}
