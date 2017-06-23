package com.dragonfruit.Parseux;

import com.dragonfruit.ResourceAsStream;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ExcelIteratorTest {
    @Test
    public void iteratedCorrectly() throws IOException {
        MatcherAssert.assertThat(
            "Each rows are iterated correctly",
            new IteratorIterable<>(
                new ExcelIterator(
                    new XSSFWorkbook(
                        new ResourceAsStream("excel/test.xlsx").stream()
                    )
                )
            ),
            Matchers.contains(
                Matchers.is("jed,24.0"),
                Matchers.is("aisyl,20.0"),
                Matchers.is("linux,23.0"),
                Matchers.is("juan,29.0")
            )
        );
    }

    @Test
    public void iteratedCorrectlyWithBlankSheet() throws IOException {
        MatcherAssert.assertThat(
            "Each rows are iterated correctly, even with blank sheet",
            new IteratorIterable<>(
                new ExcelIterator(
                    new XSSFWorkbook(
                        new ResourceAsStream("excel/test-trail-blank-sheet.xlsx").stream()
                    )
                )
            ),
            Matchers.contains(
                Matchers.is("jed,24.0"),
                Matchers.is("aisyl,20.0"),
                Matchers.is("linux,23.0"),
                Matchers.is("juan,29.0")
            )
        );
    }

    @Ignore
    @Test
    public void iteratedCorrectlyBetweenBlankSheet() throws IOException {
        MatcherAssert.assertThat(
            "Each rows are iterated correctly, between blank sheets",
            new IteratorIterable<>(
                new ExcelIterator(
                    new XSSFWorkbook(
                        new ResourceAsStream("excel/test-between-blank-sheet.xlsx").stream()
                    )
                )
            ),
            Matchers.contains(
                Matchers.is("jed,24.0"),
                Matchers.is("aisyl,20.0"),
                Matchers.is("linux,23.0"),
                Matchers.is("juan,29.0")
            )
        );
    }

    @Test
    public void iteratedCorrectlyScatteredSheet() throws IOException {
        MatcherAssert.assertThat(
            "Each rows are iterated correctly when scattered on multiple sheets",
            new IteratorIterable<>(
                new ExcelIterator(
                    new XSSFWorkbook(
                        new ResourceAsStream("excel/test-scattered-sheets.xlsx").stream()
                    )
                )
            ),
            Matchers.contains(
                Matchers.is("jed,24.0"),
                Matchers.is("aisyl,20.0"),
                Matchers.is("linux,23.0"),
                Matchers.is("juan,29.0")
            )
        );
    }

    @Ignore
    @Test
    public void iteratedCorrectlyBlankMiddle() throws IOException {
        MatcherAssert.assertThat(
            "Each rows are iterated correctly while middle sheet is blank",
            new IteratorIterable<>(
                new ExcelIterator(
                    new XSSFWorkbook(
                        new ResourceAsStream("excel/test-blank-middle-sheet.xlsx").stream()
                    )
                )
            ),
            Matchers.contains(
                Matchers.is("jed,24.0"),
                Matchers.is("aisyl,20.0"),
                Matchers.is("linux,23.0"),
                Matchers.is("juan,29.0")
            )
        );
    }
}