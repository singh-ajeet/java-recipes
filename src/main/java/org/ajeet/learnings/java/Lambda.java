package org.ajeet.learnings.java;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class Lambda {
    public static void main(String[] args) {
        System.out.println(isPrime(5));
        primeNumbers(5);
    }

    private static final void primeNumbers(int n){
        Stream.iterate(2, i -> i+1)
                .filter(Lambda::isPrime)
                .limit(n)
                .forEach(System.out::println);
    }

    private static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
