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

    @DataProvider(name = "addTraining")
    public static Object[][] addTraining() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "addTraining"
        );
    }
    @DataProvider(name = "editTraining")
    public static Object[][] editTraining() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "editTraining"
        );
    }
    @DataProvider(name = "addChapter")
    public static Object[][] addChapter() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "addChapter"
        );

    }
    @DataProvider(name = "addContent")
    public static Object[][] addContent() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "addContent"
        );
    }
    @DataProvider(name = "editChapter")
    public static Object[][] editChapter() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "editChapter"
        );
    }
    @DataProvider(name = "editContent")
    public static Object[][] editContent() {
        return ExcelReader.getSheetData(
                "src/test/resources/TestData.xlsx",
                "editContent"
        );
    }

}
