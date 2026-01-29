package pages.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.BasePage;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class EmployeeListPage extends BasePage {

    protected String browserName;

    public EmployeeListPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
    }

    public EmployeeListPage(WebDriver driver, String browserName) {
            super(driver);
            this.browserName = browserName;
            PageFactory.initElements(driver, this);
    }
    @FindBy(id= "button-add-employee")
    private WebElement addEmployeeButton;

    @FindBy(id ="name")
    private WebElement employeeNameInput;

    @FindBy(id ="email")
    private WebElement employeeEmailInput;

    @FindBy(id= "employeeId")
    private WebElement employeeIDInput;

    @FindBy(id="phoneNumber")
    private WebElement employeePhoneInput;

    @FindBy(id="division")
    private WebElement employeeDivisionDropdown;

    @FindBy(id="employeeRole")
    private WebElement employeeRoleInput;

    @FindBy(css = "button[id*='menu-button'][aria-haspopup='listbox']")
    private WebElement angkatanDropdown;

    @FindBy(xpath="//div[contains(text(),'2025 Genap')]")
    private WebElement angkatan2025Genap;


    @FindBy(id = "dateOfBirth")
    private WebElement birthDateInput;

    @FindBy(id="address")
    private WebElement employeeAddressInput;

    @FindBy(id="nik")
    private WebElement employeeNIKInput;

    @FindBy(id="npwp")
    private WebElement employeeNPWPInput;

    @FindBy(id="button-add-employee-submit")
    private WebElement saveEmployeeButton;

    @FindBy(xpath = "//button[contains(text(),'Edit') or contains(@class,'edit')]")
    private WebElement editEmployeeButton;

    @FindBy(xpath = "//p[contains(text(),'Success create employee')]")
    WebElement successCreateEmployeeNotification;

    @FindBy(xpath = "//p[contains(text(),'Success update employee')]")
    WebElement successEditEmployeeNotification;

    @FindBy(xpath = "//p[contains(text(),'Harap isi field yang wajib diisi.')]")
    WebElement failedCreateEmployeeNotification;

    @FindBy(xpath = "//button[text()='Detail']")
    WebElement detailEmployeeButton;

    @FindBy(xpath ="//input[@placeholder='Search name, e-mail, phone...']")
    private WebElement searchEmployeeInput;

    // FILTER DROPDOWN ANGKATAN
    @FindBy(xpath = "//span[text()='▼']")
    private WebElement filterDropdown;

    // ANGKATAN MENU ITEMS
    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='2024 Ganjil']")
    private WebElement semester2024Ganjil;

    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='2024 Genap']")
    private WebElement semester2024Genap;

    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='2025 Ganjil']")
    private WebElement semester2025Ganjil;

    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='2025 Genap']")
    private WebElement semester2025Genap;

    // VERIFY SELECTED ANGKATAN
    @FindBy(xpath = "//p[normalize-space()='2025 Genap']")
    private WebElement selectedAngkatanText;

    @FindBy(id = "menu-button-admin-employee-action")
    private WebElement adminEmployeeActionDropdown;

    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='Download']")
    private WebElement downloadMenuItem;

    @FindBy(xpath = "//button[@role='menuitem' and @data-action='import']")
    private WebElement importMenuItem;

    @FindBy(xpath = "//button[@role='menuitem' and @data-action='transfer']")
    private WebElement transferMenuItem;

    @FindBy(id = "input-file-import-employees-input")
    private WebElement uploadFileInput;

    @FindBy(id = "import-employees-button")
    private WebElement importEmployeesButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[3]")
    private WebElement firstResultNameCell;

    @FindBy(xpath = "//p[contains(text(),'Succes Import Data')]")
    private WebElement successImportDataNotification;


    public void clickAddEmployeeButton() {
        click(addEmployeeButton);
    }

    public void inputEmployeeName(String name) {
        clearAndType(employeeNameInput, name);
    }

    public void inputEmployeeEmail(String email) {
        clearAndType(employeeEmailInput, email);
    }

    public void inputEmployeeID(String id) {
        clearAndType(employeeIDInput, id);
    }

    public void inputEmployeePhone(String phone) {
        clearAndType(employeePhoneInput, phone);
    }

    public void selectDivisionBusiness() {
        String[] possibleDivisions = {
            "Business",
            "Business {browserName}",
            "business",
            "BUSINESS"
        };
        selectDropdownByVisibleTextWithFallback(employeeDivisionDropdown, possibleDivisions, browserName);
    }

    public void selectEmployeeRole(String role) {
        clearAndType(employeeRoleInput, role);
    }

    public void inputBirthDate(String date) {
        clearAndType(birthDateInput, date);
    }

    public void inputEmployeeAddress(String address) {
        clearAndType(employeeAddressInput, address);
    }
    
    public void inputEmployeeNIK(String nik) {
        clearAndType(employeeNIKInput, nik);
    }
    
    public void inputEmployeeNPWP(String npwp) {
        clearAndType(employeeNPWPInput, npwp);
    }

    public void scrollToSaveButton() {
        scrollToElement(saveEmployeeButton);
        click(saveEmployeeButton);
    }

    public String getSuccessCreateEmployeeText() {
        return getText(successCreateEmployeeNotification);
    }

    public String getSuccessEditEmployeeText() {
        return getText(successEditEmployeeNotification);
    }

    public String getFailedCreateEmployeeText() {
        return getText(failedCreateEmployeeNotification);
    }

    public void clickDetailEmployeeButton() {
        scrollToElement(detailEmployeeButton);
        waitForVisibility(detailEmployeeButton);
        click(detailEmployeeButton);
    }

    public void inputSearchEmployee(String keyword) {
        clearAndType(searchEmployeeInput, keyword);
        waitMillis(2000);
    }
    public void clickFilterDropdown() {
        click(filterDropdown);
    }

    public void selectSemester2024Ganjil() {
        click(semester2024Ganjil);
    }

    public void selectSemester2024Genap() {
        click(semester2024Genap);
    }

    public void selectSemester2025Ganjil() {
        click(semester2025Ganjil);
    }

    public void selectSemester2025Genap() {
        click(semester2025Genap);
    }

    public String getSelectedAngkatan() {
        return getText(selectedAngkatanText);
    }

    public void clickAdminEmployeeActionDropdown() {
        click(adminEmployeeActionDropdown);
    }
    public void clickDownloadMenuItem() {
        waitForElementToBeClickable(downloadMenuItem);
        scrollToElement(downloadMenuItem);
        System.out.println("[EmployeeListPage] Clicking download menu item...");
        click(downloadMenuItem);
        System.out.println("[EmployeeListPage] Download click completed");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) {}
    }

    public void clickImportMenuItem() {
        click(importMenuItem);
    }
    public void clickTransferMenuItem() {
        click(transferMenuItem);
    }


public void uploadEmployeeFile() {

    File file = new File("src/test/resources/testDataImport.xlsx");
    String filePath = file.getAbsolutePath();
    uploadFileInput.sendKeys(filePath);
}

    public String getTextSuccessImport(){
        return getText(successImportDataNotification);
    }

    public void clickImportEmployee(){
        click(importEmployeesButton);
    }
}
