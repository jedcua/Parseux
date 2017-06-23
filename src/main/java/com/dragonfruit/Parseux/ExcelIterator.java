package com.dragonfruit.Parseux;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.util.StringJoiner;

/**
 * Custom {@link Iterator} for Excel {@link org.apache.poi.ss.usermodel.Workbook},
 * Runs through all of {@link Sheet}, then {@link Row} then concatenates {@link Cell}
 * as {@link Iterator}.
 */
public final class ExcelIterator implements Iterator<String> {
    private Iterator<Sheet> sheets;
    private Iterator<Row> rows;

    /**
     * Constructor.
     *
     * @param workbook excel workbook
     */
    public ExcelIterator(final XSSFWorkbook workbook) {
        this.sheets = workbook.iterator();
        this.rows = this.sheets.next().iterator();
    }

    @Override
    public boolean hasNext() {
        return this.rows.hasNext();
    }

    @Override
    public String next() {
        final Iterator<Cell> cells = this.rows.next().iterator();
        final StringJoiner joiner = new StringJoiner(",");

        while (cells.hasNext()) {
            joiner.add(
                extractCellValue(
                    cells.next()
                )
            );
        }

        if (!this.rows.hasNext() && this.sheets.hasNext()) {
            this.rows = this.sheets.next().iterator();
        }

        return joiner.toString();
    }

    /**
     * Attempts to convert a {@link Cell} to {@link String}, if it fails,
     * it throws {@link IllegalStateException} with message
     *
     * @param cell to convert to string
     * @return String value of cell
     */
    private String extractCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
        }

        throw new IllegalStateException(
            String.format(
                "No string conversion defined for sheet %s, cell %s with type %s",
                cell.getSheet().getSheetName(),
                cell.getAddress().formatAsString(),
                cell.getCellTypeEnum().toString()
            )
        );
    }
}
