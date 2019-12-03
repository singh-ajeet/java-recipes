package org.ajeet.learnings.java.exceptionhandling;

import java.util.Arrays;
import java.util.List;

public final class StreamExceptions {

    public static void sleepingThreads(int sleepoverTime) {
        try {
            Thread.sleep(sleepoverTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleeing(){
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        list.forEach(StreamExceptions::sleepingThreads);
    }

    public static void main(String[] args) {
   /*     List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        list.forEach(FunctionWithCheckedException.wrap(i -> Thread::sleep));
*/
    }
}
