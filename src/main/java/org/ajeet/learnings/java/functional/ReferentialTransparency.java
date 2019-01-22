package org.ajeet.learnings.java.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * If a function always return same output for same input than it is an referential transparent function
 * or sometime we called it pure function
 *
 */
public final class ReferentialTransparency {

    /**
     * This function is not referential transparent, it will not return same output for provided input
     *
     * @return
     */
    public static int generateId(int seed){
        return new Random().nextInt(seed);
    }

    /**
     *  This function is not referential transparent
     *
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> List<T> combineNotPure(List<T> first, List<T> second){
        first.addAll(second);
        return first;
    }

    /**
     *  This function is a referential transparent or pure function
     *
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> List<T> combinePure(List<T> first, List<T> second){
        List<T> result = new ArrayList<>(first.size() + second.size());
        result.addAll(first);
        result.addAll(second);
        return result;
    }
}
