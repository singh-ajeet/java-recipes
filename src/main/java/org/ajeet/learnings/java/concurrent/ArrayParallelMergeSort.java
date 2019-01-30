package org.ajeet.learnings.java.concurrent;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;

public final class ArrayParallelMergeSort {
    private static ForkJoinPool pool = new ForkJoinPool(MergeWorker.MAX_THREADS);


    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        sort(arr, (a,b) -> a.compareTo(b));
    }

    public static <T> void sort(T[] arr, Comparator<? super T> comp) {
        Object[] temp = new Object[arr.length];
        MergeWorker<T> fw = new MergeWorker<T>(0, arr.length - 1, arr, comp, temp);
        pool.invoke(fw);
    }
}