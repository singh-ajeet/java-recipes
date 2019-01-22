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
    private static final Function<String, Consumer<String>> SOCKET = (String ipAddress) -> (String message) -> {
        System.out.println("Message '" + message + "' has been sent to " + ipAddress );
    };

    public static void main(String[] args) {
        SOCKET.apply("LOCALHOST").accept("Hello !!!!");
        //Currying
        Consumer<String> localConnection = SOCKET.apply("127.0.0.1");
        localConnection.accept("Hello");
    }
}

