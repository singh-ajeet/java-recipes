package org.ajeet.learnings.java;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class IteratorOfIterator<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> iterators;
    private Iterator<T> current;

    public IteratorOfIterator(Iterable<Iterator<T>> iterables) {
        this.iterators = iterables.iterator();
    }

    @Override
    public boolean hasNext() {
        if(current == null || !current.hasNext()) {
            while(iterators.hasNext()){
                current = iterators.next();
                if(current != null && current.hasNext())
                    return true;
            }
        }
        return current != null && current.hasNext();
    }

    @Override
    public T next() {
        return current.next();
    }


    public static void main(String[] args) {
        List<String> first = Arrays.asList("A", "B", "C");
        List<String> second = Arrays.asList("1111", "222", "3333");

        IteratorOfIterator<String> itr = new IteratorOfIterator(Arrays.asList(first.iterator(), second.iterator()));

        while (itr.hasNext())
            System.out.println(itr.next());
    }
}
