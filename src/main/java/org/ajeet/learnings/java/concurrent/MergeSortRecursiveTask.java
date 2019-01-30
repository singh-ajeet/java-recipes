package org.ajeet.learnings.java.concurrent;

import java.util.concurrent.RecursiveTask;

public class MergeSortRecursiveTask extends RecursiveTask<int[]> {
    private static final int THRESOLD = 100;
    private final int[] input;
    private final int start;
    private final int end;

    public MergeSortRecursiveTask(int[] input){
        this.input = input;
        this.start = 0;
        this.end = input.length-1;
    }

    public MergeSortRecursiveTask(int[] input, int start, int end) {
        this.input = input;
        this.start = start;
        this.end = end;
    }

    @Override
    protected int[] compute() {
        if (end - start <= THRESOLD)
            return computeSequentially();

        int mid  =  (start + end) / 2;
        MergeSortRecursiveTask leftTask = new MergeSortRecursiveTask(input, start, mid);
        MergeSortRecursiveTask rightTask = new MergeSortRecursiveTask(input, mid+1, end);

        leftTask.fork();
        int[] rightResult = rightTask.compute();
        int[] leftResult = leftTask.join();

        return merge(leftResult, rightResult);
    }

    private int[] merge(int[] leftResult, int[] rightResult) {
        int size = Math.max(leftResult.length, rightResult.length);
        int[] merged = new int[size];

        return merged;
    }

    private int[] computeSequentially(){
        return null;
    }
}
