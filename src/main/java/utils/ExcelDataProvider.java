package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    private static final String TEST_DATA_PATH = "src/test/resources/TestData.xlsx";

    @DataProvider(name = "divisionData")
    public static Object[][] divisionData() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "divisionData");
    }

    @DataProvider(name = "editDivision")
    public static Object[][] editDivision() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "editDivision");
    }

    @DataProvider(name = "addEmployee")
    public static Object[][] addEmployee() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "addEmployee");
    }
    
    @DataProvider(name = "editEmployee")
    public static Object[][] editEmployee() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "editEmployee");
    }

    @DataProvider(name = "addTraining")
    public static Object[][] addTraining() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "addTraining");
    }

    @DataProvider(name = "editTraining")
    public static Object[][] editTraining() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "editTraining");
    }

    @DataProvider(name = "addChapter")
    public static Object[][] addChapter() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "addChapter");
    }

    @DataProvider(name = "addContent")
    public static Object[][] addContent() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "addContent");
    }

    @DataProvider(name = "editChapter")
    public static Object[][] editChapter() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "editChapter");
    }

    @DataProvider(name = "editContent")
    public static Object[][] editContent() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "editContent");
    }

    @DataProvider(name = "searchEmployee")
    public static Object[][] searchEmployee() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "searchEmployee");
    }

    @DataProvider(name = "addQuestion")
    public static Object[][] addQuestion() {
        return ExcelReader.getSheetData(TEST_DATA_PATH, "addQuestion");
    }
}
