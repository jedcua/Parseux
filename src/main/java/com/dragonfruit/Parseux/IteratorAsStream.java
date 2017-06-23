package com.dragonfruit.Parseux;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Encapsulates an {@link Iterator} as {@link Stream}.
 *
 * @param <T> the type of Iterator
 */
public class IteratorAsStream<T> {
    private final Iterator<T> iterator;

    /**
     * Constructor.
     *
     * @param iterator Iterator
     */
    public IteratorAsStream(final Iterator<T> iterator) {
        this.iterator = iterator;
    }

    /**
     * Returns {@link Iterator} as {@link Stream}.
     *
     * @return stream
     */
    public Stream<T> toStream() {
        final Iterable<T> iterable = () -> this.iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
