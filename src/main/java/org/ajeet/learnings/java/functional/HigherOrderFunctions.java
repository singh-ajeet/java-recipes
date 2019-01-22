package org.ajeet.learnings.java.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A higher order function is a function that takes a function as an argument, or returns a function.
 *  Example - Comparator.comparing(Coordinate::getX)
 */
public final class HigherOrderFunctions {

    public static void main(String[] args) {
        List<String> capitals = Arrays.asList("New Delhi", "New York", "Beijing");
        System.out.println(filter(capitals, string -> string.contains("New")));

        List<Coordinate> coordinates = Arrays.asList(new Coordinate(10, 20),
                new Coordinate(20, 30),
                new Coordinate(30, 40));
        coordinates.sort(Comparator.comparing(Coordinate::getX));
        System.out.println(coordinates);
     }

    /**
     * Higher order function, it accepts a predicate function
     *
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    private static class Coordinate {
        private final double x;
        private final double y;

        private Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        private double getX() {
            return x;
        }

        private double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Coordinate [" + x + "," +  y + ']';
        }
    }
 }
