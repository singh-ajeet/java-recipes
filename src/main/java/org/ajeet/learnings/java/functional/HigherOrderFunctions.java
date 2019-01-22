package org.ajeet.learnings.java.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * A higher order function is a function that takes a function as an argument, or returns a function.
 *
 */
public final class HigherOrderFunctions {

    public static void main(String[] args) {
        List<String> capitals = Arrays.asList("New Delhi", "New York", "Beijing");
        System.out.println(filter(capitals, string -> string.contains("New")));
     }

    /**
     * Higher order function
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

 }
