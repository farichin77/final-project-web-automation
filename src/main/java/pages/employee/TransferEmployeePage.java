package pages.employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import core.BasePage;

public class TransferEmployeePage extends BasePage {
    public TransferEmployeePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//tbody/tr//button")
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
        waitForVisibility(addButton);
        click(addButton);
    }

    public void selectTargetDivision(String division) {
        waitForVisibility(targetDivisionDropdown);
        Select select = new Select(targetDivisionDropdown);
        select.selectByVisibleText(division);
    }
    public void clickTransferEmployeeButton(){
        click(transferEmployeeButton);
    }
    public void clickCancelButton() {
       click (cancelButton);
    }

    public String getSuccessMessageTranferEmployee() {
        return getText(successMessageTranferEmployee);
    }
    public String getManageEmployeeListText() {
        return getText(manageEmployeeListText);
    }

}
