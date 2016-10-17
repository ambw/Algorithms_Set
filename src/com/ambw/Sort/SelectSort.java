package com.ambw.Sort;

/**
 * Created by ambw on 16/8/19.
 */
public class SelectSort extends SortExample{
    public static void sort(Comparable[] a) {

        //数组长度
        int N = a.length;

        for(int i = 0; i < N; i++) {
            int min = i;
            for(int j = i + 1; j < N; j++){
                if(less(a[j],a[min])) {
                    min = j;
                }
            }

            exch(a,min,i);
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
