package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

    public static Map<String, String> getApiTestData(String sheetName) {
        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata/testdata.xlsx");
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheet(sheetName);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                String key = row.getCell(0).getStringCellValue();
                Cell valueCell = row.getCell(2);

                if (valueCell != null) {
                    data.put(key, valueCell.toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
