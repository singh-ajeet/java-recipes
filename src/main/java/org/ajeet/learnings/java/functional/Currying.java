package org.ajeet.learnings.java.functional;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * In mathematics and computer science, currying is the technique of translating the evaluation of a function
 * that takes multiple arguments into evaluating a sequence of functions, each with a single argument.
 *
 *
 *
 */
public final class Currying {
    private static final Function<String, Consumer<String>> MAILER = (String ipAddress) -> (String message) -> {
        System.out.println(message + ":" + ipAddress );
    };
    //Currying
    private static final Consumer<String> LOCAL_MAILER =  MAILER.apply("127.0.0.1");

    public static void main(String[] args) {
        MAILER.apply("127.1.1.2").accept("Hello !!!!");
        LOCAL_MAILER.accept("Hello");
    }
}

