package com.dragonfruit.Parseux;

import org.apache.commons.collections4.iterators.IteratorIterable;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class IteratorAsStreamTest {
    @Test
    public void correctStream() {
        MatcherAssert.assertThat(
            "Creates stream in correct order",
            new IteratorIterable<>(
                new IteratorAsStream<>(
                    Arrays.asList(
                        "test1",
                        "test2",
                        "test3"
                    ).iterator()
                ).toStream().iterator()
            ),
            Matchers.contains(
                Matchers.is("test1"),
                Matchers.is("test2"),
                Matchers.is("test3")
            )
        );
    }

    @Test
    public void emptyStream() {
        MatcherAssert.assertThat(
            "Empty stream gives empty",
            new IteratorIterable<>(
                new IteratorAsStream<>(
                    Collections.emptyList().iterator()
                ).toStream().iterator()
            ),
            Matchers.emptyIterable()
        );
    }
}