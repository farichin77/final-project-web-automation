package core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
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

    protected void selectDropdownByVisibleTextWithFallback(WebElement dropdownElement, String[] possibleTexts, String browserName) {
        waitForVisibility(dropdownElement);
        waitForElementToBeClickable(dropdownElement);
        
        // Wait untuk options load
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("select option")
            ));
        
        Select select = new Select(dropdownElement);
        
        // Coba semua variasi teks
        for (String text : possibleTexts) {
            try {
                String finalText = text.replace("{browserName}", browserName != null ? browserName : "");
                select.selectByVisibleText(finalText);
                System.out.println("Selected dropdown option: " + finalText);
                return;
            } catch (NoSuchElementException e) {
                System.out.println("Option not found: " + text);
            }
        }
        
        // Fallback: pilih index pertama (setelah placeholder)
        try {
            List<WebElement> options = select.getOptions();
            if (options.size() > 1) {
                select.selectByIndex(1);
                System.out.println("Selected first available option");
            }
        } catch (Exception e) {
            throw new RuntimeException("No dropdown options available");
        }
    }
}



