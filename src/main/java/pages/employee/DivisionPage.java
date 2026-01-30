package pages.employee;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.BasePage;

import java.time.Duration;

public class DivisionPage extends BasePage {
    public DivisionPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id ="tabs-admin-employee--tab-1")
    private WebElement divisionTab;

    @FindBy(xpath = "//p[text()='Manage Division']")
    WebElement manageDivisionMenu;

    @FindBy(id = "add-division-button")
    private WebElement addDivisionButton;

    @FindBy(id = "name")
    private WebElement divisionNameInput;

    @FindBy(id = "description")
    private WebElement divisionDescInput;

    @FindBy (id = "add-division-confirm-button")
    private WebElement saveDivisionButton;

    @FindBy(xpath = "//p[contains(text(),'Success create division')]")
    WebElement successCreateDivisionNotification;

    @FindBy(id = "edit-division-button")
    private WebElement editDivisionButton;

    @FindBy(id = "edit-division-confirm-button")
    private WebElement saveEditDivisionButton;

    @FindBy(xpath = "//p[contains(text(),'Success update division')]")
    WebElement successEditDivisionNotification;

    @FindBy(xpath="//button[@id='detail-division-button']")

    private WebElement detailBusinessButton;

    @FindBy(id = "delete-division-button")
    private WebElement deleteDivisionButton;


    @FindBy(id = "delete-division-confirm-button")
    private WebElement confirmDeleteDivisionButton;

    @FindBy(xpath = "//p[contains(text(),'Success delete division')]")
    WebElement successDeleteDivisionNotification;

    @FindBy(xpath = "//input[@placeholder='Search division...']")
    private WebElement searchDivisionInput;

    @FindBy(id = "export-employee-button")
    private WebElement exportCsvButton;

    public void clickExportCsvButton() {
        click(exportCsvButton);
    }

    public void clickDivisionTab() {
        click(divisionTab);
    }

    public String getManageDivisionText() {
        return getText(manageDivisionMenu);
    }

    public void clickAddDivisionButton() {
        click(addDivisionButton);
    }


    public void enterDivisionName(String newDivisionName) {
        clearAndType(divisionNameInput, newDivisionName);
    }

    public void enterDivisionDesc(String newDivisionDesc) {
        clearAndType(divisionDescInput, newDivisionDesc);
    }



    public void clickSaveEditDivisionButton() {
        click(saveEditDivisionButton);
    }
    public String getMessageEditText() {
        return getText(successEditDivisionNotification);
    }

    public void clickDetailBusinessButton() {
        waitForVisibility(detailBusinessButton);
        click(detailBusinessButton);
    }

    public void clickEditDivisionButton() {
        click(editDivisionButton);
    }

    public void clickSaveDivisionButton() {
        click(saveDivisionButton);
    }
    public String getMessageText() {
        return getText(successCreateDivisionNotification);
    }

    public void clickDeleteDivisionButton() {
        click(deleteDivisionButton);
    }

    public void clickConfirmDeleteDivisionButton() {
        click(confirmDeleteDivisionButton);
    }

    public String getMessageDeleteText() {
        return getText(successDeleteDivisionNotification);
    }

    public void searchDivision(String divisionName){
        clearAndType(searchDivisionInput, divisionName);
        waitMillis(2000);
    }


}
