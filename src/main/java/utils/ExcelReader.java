package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static Object[][] getSheetData(String filePath, String sheetName) {

        List<Object[]> dataList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            int lastRow = sheet.getLastRowNum(); // PENTING
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            DataFormatter formatter = new DataFormatter();

            // start dari row ke-1 (skip header)
            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Object[] rowData = new Object[colCount];
                boolean isEmptyRow = true;

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = formatter.formatCellValue(cell).trim();
                    rowData[j] = value;

                    if (!value.isEmpty()) {
                        isEmptyRow = false;
                    }
                }

                // hanya ambil row yang benar-benar punya data
                if (!isEmptyRow) {
                    dataList.add(rowData);
                }
            }

            workbook.close();
            fis.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }

        return dataList.toArray(new Object[0][0]);
    }
}
