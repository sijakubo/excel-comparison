package de.sijakubo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelSheet {

    private final FormulaEvaluator formulaEvaluator;
    private final Sheet sheet;

    public ExcelSheet(Sheet sheet) {
        this.sheet = sheet;
        this.formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
    }

    protected List<Map<String, Object>> getRowValues() {
        Iterator<Row> sheetIterator = sheet.iterator();
        Iterator<Cell> headerRowIterator = sheetIterator.next().cellIterator();

        List<String> headers = new ArrayList<>();
        headerRowIterator.forEachRemaining(cell -> headers.add(cell.getStringCellValue()));

        List<Map<String, Object>> sheetValues = new ArrayList<>();
        sheetIterator.forEachRemaining(row -> {
            Map<String, Object> rowValues = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                rowValues.put(headers.get(i), getCellValue(row.getCell(i)));
            }

            sheetValues.add(rowValues);
        });

        return sheetValues;
    }

    private Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        CellValue cellValue = formulaEvaluator.evaluate(cell);
        switch (cellValue.getCellType()) {

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return formulaEvaluator.evaluate(cell);

            case ERROR:
            case _NONE:
            case BLANK:
                return null;
            default:
                throw new IllegalStateException("Unexpected value: " + cell.getCellType());
        }
    }
}
