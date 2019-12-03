package org.ajeet.learnings.java.exceptionhandling;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionWithCheckedException<T, R, E extends Exception> {
    public R apply(T t) throws E;

    public static <T, R, E extends Exception> Function<T, R> wrap(FunctionWithCheckedException<T, R, E> fe) {
        return arg -> {
            try {
                return fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}