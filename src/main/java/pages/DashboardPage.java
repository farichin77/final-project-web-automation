package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//p[text()='Dashboard']")
    WebElement dashboardText;

    @FindBy(xpath = "//p[contains(text(),'Employee')]")
    WebElement employeeMenu;

    @FindBy(xpath = "//p[text()='Training']")
    WebElement trainingMenu;

    public String getDashboardText() {
        return dashboardText.getText();
    }

    public void clickEmployeeMenu() {
        employeeMenu.click();
    }

    public void clickTrainingMenu() {
        trainingMenu.click();
    }

}

