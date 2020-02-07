package de.sijakubo;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelExtractor {

    public static List<List<Map<String, Object>>> extractWorkbookValues(File workbookFile) throws IOException {
        Workbook workbook = WorkbookFactory.create(workbookFile);
        return extractWorkbookValues(workbook);
    }

    public static List<List<Map<String, Object>>> extractWorkbookValues(Workbook workbook) {
        List<List<Map<String, Object>>> sheetValues = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheetValues.add(extractSheetValues(workbook, i));
        }

        return sheetValues;
    }

    public static List<Map<String, Object>> extractSheetValues(File workbookFile, int sheetIndex) throws IOException {
        Workbook workbook = WorkbookFactory.create(workbookFile);
        return extractSheetValues(workbook, sheetIndex);
    }

    public static List<Map<String, Object>> extractSheetValues(Workbook workbook, int sheetIndex) {
        ExcelSheet sheet = new ExcelSheet(workbook.getSheetAt(sheetIndex));
        return sheet.getRowValues();
    }
}
