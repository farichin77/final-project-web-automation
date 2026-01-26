package pages.login;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-username-or-email")
    private WebElement usernameField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "button-sign-in")
    private WebElement loginButton;

    @FindBy(xpath = "//p[contains(text(),'wrong username or password')]")
    WebElement errorMessage;


    public void enterUsername(String username) {
        clearAndType(usernameField, username);
    }

    public void enterPassword(String password) {
        clearAndType(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        waitForVisibility(errorMessage);
        return errorMessage.isDisplayed();
    }

}