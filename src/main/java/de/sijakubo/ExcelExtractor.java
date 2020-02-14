package de.sijakubo;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelExtractor {

    public static List<List<Map<String, Object>>> extractWorkbookValues(File workbookFile) throws IOException {
        return extractWorkbookValues(createWorkbookFromFile(workbookFile));
    }

    public static List<Map<String, Object>> extractSheetValues(File workbookFile, String sheetName) throws IOException {
        return extractSheetValues(createWorkbookFromFile(workbookFile), sheetName);
    }

    public static List<Map<String, Object>> extractSheetValues(File workbookFile, int sheetIndex) throws IOException {
        return extractSheetValues(createWorkbookFromFile(workbookFile), sheetIndex);
    }

    public static List<List<Map<String, Object>>> extractWorkbookValues(Workbook workbook) {
        List<List<Map<String, Object>>> sheetValues = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetValues.add(extractSheetValues(workbook, i));
        }

        return sheetValues;
    }

    public static List<Map<String, Object>> extractSheetValues(Workbook workbook, String sheetName) {
        return extractSheetValues(workbook.getSheet(sheetName));
    }

    public static List<Map<String, Object>> extractSheetValues(Workbook workbook, int sheetIndex) {
        return extractSheetValues(workbook.getSheetAt(sheetIndex));
    }

    public static List<Map<String, Object>> extractSheetValues(Sheet sheet) {
        ExcelSheet excelSheet = new ExcelSheet(sheet);
        return excelSheet.getRowValues();
    }

    private static Workbook createWorkbookFromFile(File workbookFile) throws IOException {
        return WorkbookFactory.create(workbookFile);
    }
}
