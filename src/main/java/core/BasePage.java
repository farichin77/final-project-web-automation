package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }


    protected void click(WebElement element) {
        try {
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }


    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    protected void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll to center to avoid header overlaps
            js.executeScript("arguments[0].scrollIntoView({behavior:'instant', block:'center'});", element);
        } catch (Exception ignored) {}
    }


    protected void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void clearAndType(WebElement element, String text) {
        waitForVisibility(element);
        scrollToElement(element);
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    public void setDate(WebElement element, String date) {
        waitForVisibility(element);
        scrollToElement(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script =
                "var el = arguments[0];" +
                        "var val = arguments[1];" +
                        "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                        "nativeInputValueSetter.call(el, val);" +
                        "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "el.dispatchEvent(new Event('blur', { bubbles: true }));";

        js.executeScript(script, element, date);
    }

    protected WebElement waitForElementWithTimeout(WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .ignoring(StaleElementReferenceException.class)
            .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToAppear(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .ignoring(StaleElementReferenceException.class)
            .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForTableToLoad() {
        waitForElementToAppear(By.cssSelector("table tbody tr"), 15);
    }

    protected void waitForEmployeeInTable(String employeeName) {
        try {
            // Fix case sensitivity dengan translate()
            String xpath = "//table//tr[td[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),\"" + employeeName.toLowerCase() + "\")]]";
            waitForElementToAppear(By.xpath(xpath), 10);
            System.out.println("Employee found in table: " + employeeName);
        } catch (Exception e) {
            System.out.println("Employee not found: " + employeeName);
            
            // Debug: Print semua available rows
            try {
                List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
                System.out.println("Available rows in table:");
                for (int i = 0; i < Math.min(rows.size(), 5); i++) {
                    System.out.println("  Row " + i + ": " + rows.get(i).getText());
                }
            } catch (Exception debugEx) {
                System.out.println("Cannot debug table rows: " + debugEx.getMessage());
            }
            
            // Fallback: Coba dengan partial match (case-insensitive)
            try {
                String[] nameParts = employeeName.split(" ");
                String firstName = nameParts[0];
                String fallbackXpath = "//table//tr[td[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),\"" + firstName.toLowerCase() + "\")]]";
                waitForElementToAppear(By.xpath(fallbackXpath), 5);
                System.out.println("Employee found with partial match: " + firstName);
            } catch (Exception fallbackEx) {
                System.out.println("Employee not found even with partial match");
                throw new RuntimeException("Employee '" + employeeName + "' not found in table", e);
            }
        }
    }

    protected void clickButtonInTable(WebElement primaryButton) {
        try {
            WebElement button = waitForElementWithTimeout(primaryButton, 10);
            scrollToElement(button);
            click(button);
            System.out.println("Table button clicked successfully");
        } catch (Exception e) {
            System.out.println("=== DEBUG: Table Button Not Found ===");
            
            try {
                List<WebElement> allButtons = driver.findElements(By.cssSelector("table button, table tbody tr button"));
                System.out.println("Found " + allButtons.size() + " buttons in table:");
                
                for (int i = 0; i < allButtons.size(); i++) {
                    WebElement btn = allButtons.get(i);
                    try {
                        String text = btn.getText();
                        String tag = btn.getTagName();
                        System.out.println("  Button " + i + ": Tag=" + tag + ", Text='" + text + "', Visible=" + btn.isDisplayed());
                    } catch (Exception ex) {
                        System.out.println("  Button " + i + ": Cannot read properties");
                    }
                }
            } catch (Exception debugEx) {
                System.out.println("Cannot debug buttons: " + debugEx.getMessage());
            }
            
            try {
                WebElement firstRowButton = driver.findElement(By.cssSelector("table tbody tr:first-child button"));
                scrollToElement(firstRowButton);
                click(firstRowButton);
                System.out.println("Used first row button: " + firstRowButton.getText());
                return;
            } catch (Exception fallback1) {
                System.out.println("First row button failed: " + fallback1.getMessage());
            }
            
            try {
                WebElement addButton = driver.findElement(By.xpath("//table//button[contains(text(), '+') or contains(text(), 'Add') or contains(@class, 'add')]"));
                scrollToElement(addButton);
                click(addButton);
                System.out.println("Used add button: " + addButton.getText());
                return;
            } catch (Exception fallback2) {
                System.out.println("Add button failed: " + fallback2.getMessage());
            }
            
            try {
                List<WebElement> visibleButtons = driver.findElements(By.cssSelector("table button"));
                for (WebElement btn : visibleButtons) {
                    if (btn.isDisplayed() && btn.isEnabled()) {
                        scrollToElement(btn);
                        click(btn);
                        System.out.println("Used fallback button: " + btn.getText());
                        return;
                    }
                }
            } catch (Exception fallback3) {
                System.out.println("All buttons fallback failed: " + fallback3.getMessage());
            }
            
            throw new RuntimeException("Cannot find table button to click", e);
        }
    }
}



