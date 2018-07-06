package com.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ExcelDataParser {
    static DataFormatter formatter;

    @DataProvider(name = "Excel")
    public static Object[][] testDataGenerator(Method m) throws IOException {
        if (m.getName().equalsIgnoreCase("LoginPageTest")) {
            FileInputStream file = new FileInputStream("dataSheets/TestSheet.xlsx");
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet credntialSheet = book.getSheetAt(1);
            int numberOfRowData = credntialSheet.getRow(1).getPhysicalNumberOfCells();
            System.out.println(numberOfRowData);
            int ColNum = credntialSheet.getRow(0).getLastCellNum();
            //int ColNum= Row.getLastCellNum();
            Object[][] testData = new Object[numberOfRowData][ColNum - 1];
            formatter = new DataFormatter();
            for (int i = 0; i < numberOfRowData; i++) {
                String username = formatter.formatCellValue(credntialSheet.getRow(i).getCell(0));
                String password = formatter.formatCellValue(credntialSheet.getRow(i).getCell(1));
                testData[i][0] = username;
                testData[i][1] = password;
                System.out.println(i + " " + testData[i][0] + " " + testData[i][1]);
            }
            return testData;
        } else if (m.getName().equalsIgnoreCase("tc_002_signUp_functionality")) {
            FileInputStream file = new FileInputStream("./TestData/TestSheet.xlsx");
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet signUpSheet = book.getSheet("SignUpData");
            int numberOfData = signUpSheet.getPhysicalNumberOfRows();
            Object[][] testData = new Object[numberOfData][3];
            for (int i = 0; i < numberOfData; i++) {
                testData[i][0] = signUpSheet.getRow(i).getCell(0).getStringCellValue();
                testData[i][1] = signUpSheet.getRow(i).getCell(1).getStringCellValue();
                testData[i][2] = signUpSheet.getRow(i).getCell(2).getStringCellValue();
                // System.out.println(i + " " + testData[i][0] + " " + testData[i][1] + " " + testData[i][2]);
            }
            return testData;

        } else {
            Object[][] testData = new Object[2][3];
            return testData;
        }
    }


}





