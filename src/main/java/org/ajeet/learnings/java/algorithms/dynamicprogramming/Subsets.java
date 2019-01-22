package org.ajeet.learnings.java.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Subsets {

    public static void main(String[] args) {
        List<Integer> set = Arrays.asList(1,2,3);
        System.out.println(subsets(set));
    }

    public static List<List<Integer>> subsets(List<Integer> set) {
        if(set.isEmpty()){
            List<Integer> emptySet = Collections.emptyList();
            List<List<Integer>> emptySubsets = new ArrayList<>();
            emptySubsets.add(emptySet);
            return emptySubsets;
        }
        Integer last = set.get(set.size() - 1);
        List<Integer> rightPart = set.subList(0, set.size()-1);
        List<List<Integer>> subsets = subsets(rightPart);
        List<List<Integer>> subsets2 = appendLast(subsets, last);

        subsets.addAll(subsets2);
        return subsets;
    }

    private static List<List<Integer>> appendLast(List<List<Integer>> subsets, Integer last) {
        List<List<Integer>> updatedSubsets = new ArrayList<>(subsets.size());
        for (List<Integer> subset : subsets){
            List<Integer> subsetCopy = new ArrayList<>();
            subsetCopy.addAll(subset);
            subsetCopy.add(last);
            updatedSubsets.add(subsetCopy);
        }
        return updatedSubsets;
    }
}
