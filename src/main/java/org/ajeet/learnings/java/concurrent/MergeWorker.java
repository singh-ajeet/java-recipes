package org.ajeet.learnings.java.concurrent;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

class MergeWorker<T> extends RecursiveAction {
    static int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    static int THRESHOLD = 4096/Math.min(8, MAX_THREADS);

    private int L, R;
    private T[] arr;
    private Comparator<? super T> comp;
    private Object[] temp;

    public MergeWorker(int L, int R, T[] arr, Comparator<?super T> comp, Object[] temp) {
        this.L = L;
        this.R = R;
        this.arr = arr;
        this.comp = comp;
        this.temp = temp;
    }

    @Override
    public void compute() {
        if(R-L <= THRESHOLD) {
            int M = (R+L)/2;
            mergeSort(L, M);
            mergeSort(M+1, R);
            merge(L,M,R);
        }
        else {
            int M = (R+L)/2;
            invokeAll(new MergeWorker<T>(L, M, arr, comp, temp), new MergeWorker<T>(M+1, R, arr, comp, temp));
            merge(L, M, R);
        }
    }

    private void merge(int L, int M, int R) {
        int i=L, j=M+1;
        for(int k=L; k<=R; k++) {
            if(i>M)
                temp[k] = arr[j++];
            else if(j>R || comp.compare(arr[i], arr[j])<=0)
                temp[k] = arr[i++];
            else
                temp[k] = arr[j++];
        }
        for(int k=L;k<=R; k++)
            arr[k] = (T)temp[k];
    }

    private void mergeSort(int L, int R) {
        if(L == R) return;
        int M = (R+L)/2;
        mergeSort(L, M);
        mergeSort(M+1, R);
        merge(L,M,R);
    }
}
