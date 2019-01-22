package org.ajeet.learnings.java.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncApp {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = null;
        try {
            completableFuture.complete("");
        } catch(Exception ex){
            completableFuture.completeExceptionally(ex);
        }
        //completableFuture.thenApply()
        Future<String> future = null;

    }
}
