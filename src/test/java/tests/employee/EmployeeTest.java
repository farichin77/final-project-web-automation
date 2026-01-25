package tests.employee;

import core.BaseTest;
import org.apache.hc.core5.reactor.Command;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.dashboard.DashboardPage;
import pages.employee.DetailEmployeePage;
import pages.employee.EditEmployeePage;
import pages.employee.EmployeeListPage;
import pages.employee.TranferEmployeePage;
import utils.DownloadUtil;
import utils.ExcelDataProvider;
import core.DriverManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PrimitiveIterator;

public class EmployeeTest extends BaseTest {


    private String generateUniqueEmail(String baseEmail) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "test" + timestamp + "@example.com";
    }

    @Test( priority = 1,
            dataProvider = "addEmployee",
            dataProviderClass = ExcelDataProvider.class
    )
    public void verifyAddEmployeeTest(String name, String email, String employeeId, String phoneNumber,
                                      String role, String birthDate, String address, String nik, String npwp) {

        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickAddEmployeeButton();
        employeeListPage.inputEmployeeName(name + " " + browserName);

        // Generate email unik
        String uniqueEmail = generateUniqueEmail(email);
        employeeListPage.inputEmployeeEmail(uniqueEmail);

        employeeListPage.inputEmployeeID(employeeId);
        employeeListPage.inputEmployeePhone(phoneNumber);
        employeeListPage.selectDivisionBusiness();
        employeeListPage.selectEmployeeRole(role);
        employeeListPage.inputBirthDate(birthDate);
        employeeListPage.inputEmployeeAddress(address);
        employeeListPage.inputEmployeeNIK(nik);
        employeeListPage.inputEmployeeNPWP(npwp);
        employeeListPage.scrollToSaveButton();

        Assert.assertEquals(
                employeeListPage.getSuccessCreateEmployeeText(),
                "Success create employee",
                "Failed to add employee"
        );
    }

    @Test(priority = 2)
    public void verifyAddEmployeeWithEmptyFieldsTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickAddEmployeeButton();

        employeeListPage.scrollToSaveButton();

        // Verifikasi pesan error muncul
        Assert.assertEquals(
                employeeListPage.getFailedCreateEmployeeText(),
                "Harap isi field yang wajib diisi.",
                "Failed to show validation error for empty fields"
        );
    }

    @Test( priority =3,
            dataProvider = "editEmployee",
            dataProviderClass = ExcelDataProvider.class
    )
    public void verifyEditEmployeeTest(String name, String email, String employeeId, String phoneNumber,
                                       String role, String birthDate, String address, String nik, String npwp) {

        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama " + browserName);

        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickEditEmployeeDetail();

        EditEmployeePage editEmployeePage = new EditEmployeePage(DriverManager.getDriver());

        editEmployeePage.editEmployeeName(name + " " + browserName);

        String uniqueEmail = generateUniqueEmail(email);
        editEmployeePage.editEmployeeEmail(uniqueEmail);
        editEmployeePage.editEmployeeId(employeeId);
        editEmployeePage.editEmployeePhone(phoneNumber);
        editEmployeePage.saveChanges();
        Assert.assertEquals(
                employeeListPage.getSuccessEditEmployeeText(),
                "Success update employee",
                "Failed to edit employee"
        );
    }

    @Test(priority =4)
    public void inactivateAccountEmployee() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama " + browserName);
        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickActivationEmployee();
        detailEmployeePage.clickActivationEmployeeConfirm();

        // Verifikasi bahwa akun telah dinonaktifkan
        Assert.assertEquals(detailEmployeePage.getSuccessInactivateEmployeeText(),
                "Succes inactivate employee account",
                "Failed to inactivate employee account"
        );
    }

    @Test (priority = 5)
    public void activateAccountEmployee() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama " + browserName);
        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickActivationEmployee();
        detailEmployeePage.clickActivationEmployeeConfirm();

        // Verifikasi bahwa akun telah diaktifkan
        Assert.assertEquals(detailEmployeePage.getSuccessActivateEmployeeText(),
                "Succes activate employee account",
                "Failed to activate employee account"
        );
    }

    @Test (priority= 6)
    public void verifyAssignedProgramTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama " + browserName);
        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickAssignedProgramsTab();

        // Verifikasi bahwa program yang ditugaskan muncul
        Assert.assertEquals(
                detailEmployeePage.getVerifyNameText(),
                "Name",
                "Failed to display assigned programs"
        );
    }


    @Test(priority =7)
    public void verifyResendEmailAccount() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee("andi pratama " + browserName);
        employeeListPage.clickDetailEmployeeButton();

        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        detailEmployeePage.clickResendEmailButton();
        detailEmployeePage.clickResendEmailConfirmButton();

        // Verifikasi bahwa email telah dikirim ulang
        Assert.assertEquals(
                detailEmployeePage.getSuccessResendEmailText(),
                "Success Resend Email",
                "Failed to resend email"
        );
    }

    @Test (priority=8)
    public void verifyFilterByBatchTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickFilterDropdown();
        employeeListPage.selectSemester2024Ganjil();

        employeeListPage.clickFilterDropdown();
        employeeListPage.selectSemester2024Genap();

        employeeListPage.clickFilterDropdown();
        employeeListPage.selectSemester2025Ganjil();

        employeeListPage.clickFilterDropdown();
        employeeListPage.selectSemester2025Genap();

        Assert.assertEquals(
                employeeListPage.getSelectedAngkatan(),
                "2025 Genap",
                "Failed to filter by batch"
        );
    }

    @Test (priority =9)
    public void verifyDownloadEmployeeListTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        DownloadUtil.clearExcelFiles();
        employeeListPage.clickAdminEmployeeActionDropdown();
        employeeListPage.clickDownloadMenuItem();

        boolean isDownloaded = DownloadUtil.waitForExcelFile(30);

        Assert.assertTrue(
                isDownloaded,
                "File Excel tidak ditemukan di " + DownloadUtil.getDownloadDir()
        );

    }
    @Test (priority =10)
    public void verifyImportEmployeeTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickAdminEmployeeActionDropdown();
        employeeListPage.clickImportMenuItem();

        employeeListPage.uploadEmployeeFile();
        employeeListPage.clickImportEmployee();

        Assert.assertEquals(
                employeeListPage.getTextSuccessImport(),
                "Succes Import Data",
                "Import data failed");
    }


    @Test(priority = 11)
    public void verifyTranferEmployeeTest() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();


        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickAdminEmployeeActionDropdown();
        employeeListPage.clickTransferMenuItem();

        TranferEmployeePage tranferEmployeePage = new TranferEmployeePage(DriverManager.getDriver());
        tranferEmployeePage.searchByNameOrId("andi pratama " + browserName);
        tranferEmployeePage.clickAddButton();
        tranferEmployeePage.selectTargetDivision("Busineess");

        tranferEmployeePage.clickTransferEmployeeButton();

        Assert.assertEquals(
                tranferEmployeePage.getSuccessMessageTranferEmployee(),
                "Berhasil mengubah data",
                "Failed to transfer employee"
        );

    }

    @Test (priority =12)
    public void verifyCancelButtonTranfer() {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.clickAdminEmployeeActionDropdown();
        employeeListPage.clickTransferMenuItem();

        TranferEmployeePage tranferEmployeePage = new TranferEmployeePage(DriverManager.getDriver());
        tranferEmployeePage.searchByNameOrId("andi pratama " + browserName);
        tranferEmployeePage.clickAddButton();
        tranferEmployeePage.selectTargetDivision("Busineess");


        tranferEmployeePage.clickCancelButton();

        Assert.assertEquals(
                tranferEmployeePage.getManageEmployeeListText(),
                "Manage Employee List",
                "Failed to cancel transfer employee"
        );
    }

    @Test( priority = 13,
            dataProvider = "searchEmployee",
            dataProviderClass = ExcelDataProvider.class
    )
    public void verifySearchEmployeeTest(String searchKeyword, String expectedName) {
        loginValid();

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickEmployeeMenu();

        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        employeeListPage.inputSearchEmployee(searchKeyword);

        Assert.assertEquals(
                employeeListPage.getNameSearching(),
                expectedName,
                "Employee not displayed for keyword: " + searchKeyword
        );
    }

}


