package pages.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;

public class EmployeeListPage extends BasePage {

    public EmployeeListPage(WebDriver driver) {
            super(driver);
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

    @FindBy(xpath = "//button[contains(@id,'button-detail-employee')]")
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



    public void clickAddEmployeeButton() {
        addEmployeeButton.click();
    }

    public void clickEditEmployeeButton() {
        editEmployeeButton.click();
    }

    public void inputEmployeeName(String name) {
        employeeNameInput.sendKeys(name);
    }

    public void inputEmployeeEmail(String email) {
        employeeEmailInput.sendKeys(email);
    }

    public void inputEmployeeID(String id) {
        employeeIDInput.sendKeys(id);
    }

    public void inputEmployeePhone(String phone) {
        employeePhoneInput.sendKeys(phone);
    }

    public void selectDivisionBusiness() {
        Select select = new Select(employeeDivisionDropdown);
        select.selectByVisibleText("Business");
    }

    public void selectEmployeeRole(String role) {
        employeeRoleInput.sendKeys(role);
    }

    public void clickAngkatanDropdown() {
        angkatanDropdown.click();
    }
    
    public void selectAngkatan2025Genap() {
        angkatan2025Genap.click();
    }

    public void inputBirthDate(String date) {
        birthDateInput.click();
        birthDateInput.clear();
        birthDateInput.sendKeys(date);
    }

    public void inputEmployeeAddress(String address) {
        employeeAddressInput.sendKeys(address);
    }
    
    public void inputEmployeeNIK(String nik) {
        employeeNIKInput.sendKeys(nik);
    }
    
    public void inputEmployeeNPWP(String npwp) {
        employeeNPWPInput.sendKeys(npwp);
    }

    public void scrollToSaveButton() {
        scrollToElement(saveEmployeeButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickWhenReady(saveEmployeeButton);
    }


    public String getSuccessCreateEmployeeText() {
        return successCreateEmployeeNotification.getText();
    }

    public String getSuccessEditEmployeeText() {
        return successEditEmployeeNotification.getText();
    }

    public String getFailedCreateEmployeeText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(d -> failedCreateEmployeeNotification.isDisplayed());
            return failedCreateEmployeeNotification.getText();
        } catch (Exception e) {
            System.out.println("Error message not found: " + e.getMessage());
            return "";
        }

    }
    public void clickDetailEmployeeButton() {
        detailEmployeeButton.click();
    }

    public void inputSearchEmployee(String keyword) {
        searchEmployeeInput.sendKeys(keyword);
    }
    public void clickFilterDropdown() {
        filterDropdown.click();
    }

    public void selectSemester2024Ganjil() {
        semester2024Ganjil.click();
    }

    public void selectSemester2024Genap() {
        semester2024Genap.click();
    }

    public void selectSemester2025Ganjil() {
        semester2025Ganjil.click();
    }

    public void selectSemester2025Genap() {
        semester2025Genap.click();
    }

    public String getSelectedAngkatan() {
        return selectedAngkatanText.getText();
    }

    public void clickAdminEmployeeActionDropdown() {
        adminEmployeeActionDropdown.click();
    }
    public void clickDownloadMenuItem() {
        downloadMenuItem.click();
    }
    public void clickImportMenuItem() {
        importMenuItem.click();
    }
    public void clickTransferMenuItem() {
        transferMenuItem.click();
    }


}
