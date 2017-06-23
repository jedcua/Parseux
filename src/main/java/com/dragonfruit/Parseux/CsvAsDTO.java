package com.dragonfruit.Parseux;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * @param streams
     * @param dtoClass
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
     * @param bufferedReader
     * @param dtoClass
     */
    public CsvAsDTO(final BufferedReader bufferedReader, final Class<T> dtoClass) {
        this(bufferedReader.lines(), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param inputStreamReader
     * @param dtoClass
     */
    public CsvAsDTO(final InputStreamReader inputStreamReader, final Class<T> dtoClass) {
        this(new BufferedReader(inputStreamReader), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param byteArrayInputStream
     * @param dtoClass
     */
    public CsvAsDTO(final ByteArrayInputStream byteArrayInputStream, final Class<T> dtoClass) {
        this(new InputStreamReader(byteArrayInputStream), dtoClass);
    }

    /**
     * Constructor.
     *
     * @param bytes
     * @param dtoClass
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
