package org.ajeet.learnings.java.exceptionhandling;

import java.util.function.Consumer;

/**
 * This class wrapped a checked exception to runtime exception.
 *
 */
@FunctionalInterface
public interface ExceptionHandler<T, E extends Exception> {

    public void accept(T target) throws E;

    public static <T> Consumer<T> handleException(ExceptionHandler<T, Exception> handlingConsumer) {
        return obj -> {
            try {
                handlingConsumer.accept(obj);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}

