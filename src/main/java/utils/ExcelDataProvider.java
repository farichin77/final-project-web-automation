package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "divisionData")
    public static Object[][] divisionData() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "divisionData"

        );
    }

    @DataProvider(name = "editDivision")
    public static Object[][] editDivision() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "editDivision"
        );
    }
    @DataProvider(name = "addEmployee")
    public static Object[][] addEmployee() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "addEmployee"
        );
    }
    
    @DataProvider(name = "editEmployee")
    public static Object[][] editEmployee() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "editEmployee"
        );
    }
}
