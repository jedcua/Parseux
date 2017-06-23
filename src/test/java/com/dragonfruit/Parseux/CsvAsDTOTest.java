package com.dragonfruit.Parseux;

import com.dragonfruit.ResourceAsStream;
import com.dragonfruit.dto.TestDTO;
import org.apache.poi.util.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public final class CsvAsDTOTest {
    @Test
    public void parsedSize() {
        MatcherAssert.assertThat(
            "DTOs has correct size",
            new CsvAsDTO<>(
                new InputStreamReader(
                    new ResourceAsStream("test.csv").stream()
                ),
                TestDTO.class
            ).asDTOs(),
            IsCollectionWithSize.hasSize(4)
        );
    }

    @Test
    public void parsedWithByteArray() throws IOException {
        MatcherAssert.assertThat(
            "DTOs are parsed correctly with byte array",
            new CsvAsDTO<>(
                IOUtils.toByteArray(
                    new ResourceAsStream("test.csv").stream()
                ),
                TestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
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
                    new ResourceAsStream("test.csv").stream()
                ),
                TestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
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
                    new ResourceAsStream("test-whitespace.csv").stream()
                ),
                TestDTO.class
            ).asDTOs(),
            Matchers.contains(
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "jed", 24
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "aisyl", 20
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
                        "linux", 23
                    )
                ),
                SamePropertyValuesAs.samePropertyValuesAs(
                    new TestDTO(
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
                new ResourceAsStream("test-trail-comma.csv").stream()
            ),
            TestDTO.class
        ).asDTOs();
    }

    @Test(expected = UncheckedIOException.class)
    public void throwExceptionBadData() {
        new CsvAsDTO<>(
            new InputStreamReader(
                new ResourceAsStream("test-bad-data.csv").stream()
            ),
            TestDTO.class
        ).asDTOs();
    }

    @Test(expected = UncheckedIOException.class)
    public void throwExceptionMissingData() {
        new CsvAsDTO<>(
            new InputStreamReader(
                new ResourceAsStream("test-missing-data.csv").stream()
            ),
            TestDTO.class
        ).asDTOs();
    }
}