package com.dragonfruit;

import java.io.InputStream;

/**
 * Resource as stream
 */
public class ResourceAsStream {
    private final String filepath;

    /**
     * Constructor.
     *
     * @param filepath filepath
     */
    public ResourceAsStream(final String filepath) {
        this.filepath = filepath;
    }

    /**
     * Return filepath as {@link InputStream}
     *
     * @return input stream
     */
    public final InputStream stream() {
        return this
            .getClass()
            .getClassLoader()
            .getResourceAsStream(this.filepath);
    }
}
