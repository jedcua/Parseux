package com.dragonfruit.Parseux;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Class representing parsed CSV as DTO
 * @param <T> the type of any DTO class
 */
public final class CsvAsDTO<T> {
    private final Stream<String> streams;
    private final Class<T> dtoClass;
    private final ObjectReader reader;

    /**
     * Constructor.
     *
     * @param streams {@link Stream}
     * @param dtoClass DTO
     */
    public CsvAsDTO(final Stream<String> streams, final Class<T> dtoClass) {
        this.streams = streams;
        this.dtoClass = dtoClass;
        this.reader = new CsvMapper()
            .readerWithSchemaFor(this.dtoClass)
            .with(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS)
            .without(CsvParser.Feature.ALLOW_TRAILING_COMMA);
    }

    /**
     * Constructor.
     *
     * @param iterator {@link Iterator} of {@link String}, each is a concat of CSV row
     * @param dtoClass DTO
     */
    public CsvAsDTO(final Iterator<String> iterator, final Class<T> dtoClass) {
        this(StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false
            ), dtoClass
        );
    }

    /**
     * Constructor.
     *
     * @param bufferedReader {@link BufferedReader}
     * @param dtoClass DTO
     */
    public CsvAsDTO(final BufferedReader bufferedReader, final Class<T> dtoClass) {
        this(bufferedReader.lines(), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param inputStreamReader {@link InputStreamReader}
     * @param dtoClass DTO
     */
    public CsvAsDTO(final InputStreamReader inputStreamReader, final Class<T> dtoClass) {
        this(new BufferedReader(inputStreamReader), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param byteArrayInputStream {@link ByteArrayInputStream}
     * @param dtoClass DTO
     */
    public CsvAsDTO(final ByteArrayInputStream byteArrayInputStream, final Class<T> dtoClass) {
        this(new InputStreamReader(byteArrayInputStream), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param bytes Array of bytes
     * @param dtoClass DTO
     */
    public CsvAsDTO(final byte[] bytes, final Class<T> dtoClass) {
        this(new ByteArrayInputStream(bytes), dtoClass);
    }

    /**
     * Parse as {@link List} of DTO.
     *
     * @return list of DTO
     */
    public final List<T> asDTOs() {
        try (Stream<String> stream = this.streams) {
            return stream
                .map(this::castToDTO)
                .collect(Collectors.toList());
        }
    }

    /**
     * Helper function.
     *
     * @param csvStr - csv row joined as String
     * @return casted object
     * @throws UncheckedIOException if parsing fails
     */
    private T castToDTO(final String csvStr) {
        try {
            return this.dtoClass.cast(
                reader.readValue(csvStr)
            );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
