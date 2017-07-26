package com.dragonfruit.Parseux;

import com.dragonfruit.ResourceAsStream;
import com.dragonfruit.dto.CsvTestDTO;
import com.dragonfruit.dto.TsvTestDTO;
import org.apache.poi.util.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Arrays;

public final class CsvAsDTOTest {
    @Test
    public void parsedSize() {
        MatcherAssert.assertThat(
            "DTOs has correct size",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("csv/test.csv").stream()
                ),
                CsvTestDTO.class
            ).asDTOs(),
            IsCollectionWithSize.hasSize(4)
        );
    }

    @Test
    public void parsedWithIterator() throws IOException {
        MatcherAssert.assertThat(
            "DTOs are parsed correctly with iterators",
            new CsvAsDTO<>(
                Arrays.asList(
                    "jed,24",
                    "aisyl,20",
                    "linux,23",
                    "juan,29"
                ).iterator(),
                CsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "juan", 29
                    )
                )
            )
        );
    }

    @Test
    public void parsedWithByteArray() throws IOException {
        MatcherAssert.assertThat(
            "DTOs are parsed correctly with byte array",
            new CsvAsDTO<>(
                IOUtils.toByteArray(
                    new ResourceAsStream("csv/test.csv").stream()
                ),
                CsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "juan", 29
                    )
                )
            )
        );
    }

    @Test
    public void parsedCorrectly() {
        MatcherAssert.assertThat(
            "DTOs are parsed correctly",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("csv/test.csv").stream()
                ),
                CsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "juan", 29
                    )
                )
            )
        );
    }

    @Test
    public void parsedCorrectlyExtraWhitespace() {
        MatcherAssert.assertThat(
            "DTOs are parsed correctly, even if excessive whitespace",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("csv/test-whitespace.csv").stream()
                ),
                CsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new CsvTestDTO(
                        "juan", 29
                    )
                )
            )
        );
    }

    @Test(expected = UncheckedIOException.class)
    public void throwExceptionTrailingComma() {
        new CsvAsDTO<>(
            new InputStreamReader(
                new ResourceAsStream("csv/test-trail-comma.csv").stream()
            ),
            CsvTestDTO.class
        ).asDTOs();
    }

    @Test(expected = UncheckedIOException.class)
    public void throwExceptionBadData() {
        new CsvAsDTO<>(
            new InputStreamReader(
                new ResourceAsStream("csv/test-bad-data.csv").stream()
            ),
            CsvTestDTO.class
        ).asDTOs();
    }

    @Test(expected = UncheckedIOException.class)
    public void throwExceptionMissingData() {
        new CsvAsDTO<>(
            new InputStreamReader(
                new ResourceAsStream("csv/test-missing-data.csv").stream()
            ),
            CsvTestDTO.class
        ).asDTOs();
    }

    @Test
    public void parsedTsvCorrectly() {
        MatcherAssert.assertThat(
            "Tab seperated values parsed correctly",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("tsv/test.tsv").stream()
                ),
                TsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000001", 100.0
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000002", 200.0
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000003", 12.0
                    )
                )
            )
        );
    }

    @Test
    public void parsedTsvCorrectlyNoQuotes() {
        MatcherAssert.assertThat(
            "Tab seperated values with no quotes parsed correctly",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("tsv/test-no-quotes.tsv").stream()
                ),
                TsvTestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000001", 100.0
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000002", 200.0
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TsvTestDTO(
                        "9170000003", 12.0
                    )
                )
            )
        );
    }
}