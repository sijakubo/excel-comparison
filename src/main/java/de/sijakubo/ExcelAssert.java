package de.sijakubo;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.ComparisonFailure;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelAssert {

    public static void assertWorkbookEquals(File actual, File expected) throws IOException {
        Workbook actualWorkbook = WorkbookFactory.create(actual);
        Workbook expectedWorkbook = WorkbookFactory.create(expected);

        assertWorkbookEquals(actualWorkbook, expectedWorkbook);
    }

    public static void assertSheetEquals(File actual, File expected, int sheetIndex) throws IOException {
        List<Map<String, Object>> actualSheetValues = ExcelExtractor.extractSheetValues(actual, sheetIndex);
        List<Map<String, Object>> expectedSheetValues = ExcelExtractor.extractSheetValues(expected, sheetIndex);

        compare(actualSheetValues, expectedSheetValues);
    }

    public static void assertWorkbookEquals(Workbook actual, Workbook expected) {
        List<List<Map<String, Object>>> actualWorkbookValues = ExcelExtractor.extractWorkbookValues(actual);
        List<List<Map<String, Object>>> expectedWorkbookValues = ExcelExtractor.extractWorkbookValues(expected);

        compare(actualWorkbookValues, expectedWorkbookValues);
    }

    public static void assertSheetEquals(Workbook actual, Workbook expected, int sheetIndex) {
        List<Map<String, Object>> actualSheetValues = ExcelExtractor.extractSheetValues(actual, sheetIndex);
        List<Map<String, Object>> expectedSheetValues = ExcelExtractor.extractSheetValues(expected, sheetIndex);

        compare(actualSheetValues, expectedSheetValues);
    }

    public static void assertSheetEquals(Sheet actual, Sheet expected) {
        List<Map<String, Object>> actualSheetValues = new ExcelSheet(actual).getRowValues();
        List<Map<String, Object>> expectedSheetValues = new ExcelSheet(expected).getRowValues();

        compare(actualSheetValues, expectedSheetValues);
    }

    private static void compare(Object actual, Object expected) {
        if (!expected.equals(actual)) {
            throw new ComparisonFailure("The given Values mismatch", String.valueOf(actual), String.valueOf(expected));
        }
    }
}
