package pages.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import core.BasePage;

public class TranferEmployeePage extends BasePage {
    public TranferEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//tbody/tr[2]/td[4]/button[1]//*[name()='svg']//*[name()='path' and contains(@fill,'currentCol')]")
    private WebElement addButton;

    @FindBy(xpath = "//button[.//svg]")
    private WebElement closeButton;

    @FindBy(xpath = "//select[option[normalize-space()='Select Division']]")
    private WebElement targetDivisionDropdown;

    @FindBy(xpath = "//button[normalize-space()='Transfer Employee']")
    private WebElement transferEmployeeButton;

    @FindBy(xpath ="//button[normalize-space()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@type='search' and @placeholder='Search name, ID...']")
    private WebElement searchInput;

    @FindBy(xpath = "//p[contains(text(),'Berhasil mengubah data')]")
    public WebElement successMessageTranferEmployee;

    @FindBy(xpath = "//p[normalize-space()='Manage Employee List']")
    private WebElement manageEmployeeListText;



    public void clickAddButton(){
        addButton.click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }
    public void selectTargetDivision(String division) {
        targetDivisionDropdown.sendKeys(division);
    }
    public void clickTransferEmployeeButton(){
        transferEmployeeButton.click();
    }
    public void clickCancelButton() {
        cancelButton.click();
    }
    public void searchByNameOrId(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
    }
    public String getSuccessMessageTranferEmployee() {
        return successMessageTranferEmployee.getText();
    }
    public String getManageEmployeeListText() {
        return manageEmployeeListText.getText();
    }

}
